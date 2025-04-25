package com.adityapdev.ChaChing_api.service;

import com.adityapdev.ChaChing_api.dto.arbitrage.ArbitrageDto;
import com.adityapdev.ChaChing_api.entity.CurrencyGraph;
import com.adityapdev.ChaChing_api.exception.ConflictException;
import com.adityapdev.ChaChing_api.service.interfaces.IArbitrageService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class ArbitrageService implements IArbitrageService {

    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Detects arbitrage opportunities from a list of coin symbols using multithreaded execution of arbitrage detection algorithm
     * Checks arbitrage opportunities in parallel using a thread pool
     * If an arbitrage opportunity meets or exceeds the user provided profit margin, it is included in the final result.
     */
    @Override
    public List<ArbitrageDto> detectArbitrage(BigDecimal profitMargin, List<String> coinSymbols) {
        if (coinSymbols.size() < 3)
            throw new ConflictException("Minimum of 3 coins in the portfolio are required!");
        
        System.out.println(coinSymbols);

        List<ArbitrageDto> res = Collections.synchronizedList(new ArrayList<>());
        List<List<String>> triplets = generateTripletCombinations(coinSymbols);

        // create a thread pool with a fixed number of threads = # of available CPU cores
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        // allows us to perform concurrent tasks efficiently in parallel
        for (List<String> triplet : triplets) {
            // submit a new task to the executor for each triplet combination
            executor.submit(() -> {
                ArbitrageDto dto = detectForTriplet(profitMargin, triplet);
                // if a valid arbitrage is detected, add it to final result
                if (dto != null)
                    res.add(dto);
            });
        }

        // no new tasks will be accepted
        executor.shutdown();
        try {
            executor.awaitTermination(10, TimeUnit.MINUTES); // wait up to 10 mins
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  res;
    }

    /**
     * Detects triangular arbitrage opportunities within a given list of crypto coin symbols.
     * The function performs the following steps:
     *      1. Build a CurrencyGraph using the provided coin symbols
     *          i. Fetches real-time conversion rates between all pairs using getRate() helper func - populate the edges
     *      2. Apply the modified Floyd-Warshall algorithm to find negative cycles in the graph
     *          i. Counterintuitively, negative cycles will result in profitable cycles, if trading steps are reversed
     *      3. Search for cycles of length 3 (represented by 4 elements including start/end repetition)
     *          i. these cycles have profits greater >= specified margin
     */
    private ArbitrageDto detectForTriplet(BigDecimal profitMargin, List<String> coinSymbols) {
        CurrencyGraph graph = new CurrencyGraph(coinSymbols);
        int n = coinSymbols.size();

        // Step 1: Fetch rates
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                BigDecimal rate = (i == j) ? BigDecimal.ONE : getRate(coinSymbols.get(i), coinSymbols.get(j));
                graph.setRate(i, j, rate);
            }
        }
//        graph.displayMatrix(graph.getMatrix(), "GRAPH before Floyd-Warshall:");

        // Step 2: Floyd-Warshall
        BigDecimal[][] dist = Arrays.stream(graph.getMatrix()).map(BigDecimal[]::clone).toArray(BigDecimal[][]::new);
        int[][] succ = graph.getSuccessor();

        for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    if (dist[i][k].multiply(dist[k][j]).compareTo(dist[i][j]) < 0) {
                        dist[i][j] = dist[i][k].multiply(dist[k][j]);
                        succ[i][j] = succ[i][k];
                    }
//        graph.displayMatrix(graph.getMatrix(), "GRAPH after Floyd-Warshall:");

        // Step 3: Detect cycle
        for (int i = 0; i < n; i++) {
            if (dist[i][i].compareTo(BigDecimal.ONE) < 0) {
                List<String> cycle = findCycle(i, succ, coinSymbols);
                if (cycle.size() == 4) {
                    List<ArbitrageDto.TradeStep> tradeSteps = new ArrayList<>();
                    BigDecimal rateProduct = BigDecimal.ONE;
                    List<String> cycleDetails = new ArrayList<>();

                    for (int k = 0; k < cycle.size() - 1; k++) {
                        String fromCoin = cycle.get(k);
                        String toCoin = cycle.get(k + 1);
                        int from = coinSymbols.indexOf(fromCoin);
                        int to = coinSymbols.indexOf(toCoin);

                        BigDecimal rate = graph.getRates()[from][to];
                        rateProduct = rateProduct.multiply(rate);

                        tradeSteps.add(new ArbitrageDto.TradeStep(toCoin, fromCoin, invert(rate)));
                        cycleDetails.add(String.format("%s -> %s : %s", fromCoin, toCoin, rate.toString()));
                    }

                    BigDecimal invRateProd = invert(rateProduct);
                    BigDecimal profit = invRateProd.multiply(new BigDecimal(100)).subtract(new BigDecimal(100));

                    int stepSize = tradeSteps.size();
                    ArbitrageDto.TradeStep temp = tradeSteps.get(stepSize - 1);
                    tradeSteps.set(stepSize - 1, tradeSteps.get(stepSize - 2));
                    tradeSteps.set(stepSize - 2, temp);

                    if (profit.compareTo(profitMargin) >= 0) {
                        System.out.printf(cycleDetails.toString() + " | Rate Product: %f | Profit: %f%%\n", invRateProd, profit);
                        return new ArbitrageDto(tradeSteps, invRateProd, profit);
                    } else {
                        System.out.printf("No arbitrage opportunity found (Profit below threshold). %f%%\n", profit);
                    }
                }
            }
        }
        return null;
    }


    /**
     * Fetches live spot conversion rate between two currencies using the Coinbase API
     */
    private BigDecimal getRate(String from, String to) {
        try {
            String url = String.format("https://api.coinbase.com/v2/prices/%s-%s/spot", from, to);
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);
            Map<String, String> data = (Map<String, String>) response.get("data");
            return new BigDecimal(data.get("amount"));
        } catch (Exception e) {
            System.out.printf("Error fetching rate %s to %s: %s\n", from, to, e.getMessage());
            return new BigDecimal(1);
        }
    }


    /**
     * findCycle function uses backtracking to detect a cycle in a graph
     * Each node represents a coin
     * Each edge represent conversion rates between the coins
     * The cycle corresponds to a potential arbitrage opportunity, where traveling through the cycle will result in a profit
     * The cycle is detected using a successor matrix (succ), which holds the next node to visit when traversing from a given node.
     */
    private List<String> findCycle(int start, int[][] succ, List<String> coinSymbols) {
        Set<Integer> visited = new HashSet<>();
        List<Integer> path = new ArrayList<>();
        int u = start;
        while (!visited.contains(u)) {
            visited.add(u);
            path.add(u);
            u = succ[u][start];
        }
        int idx = path.indexOf(u);
        List<Integer> cycleIdx = path.subList(idx, path.size());
        List<String> cycle = new ArrayList<>();
        for (int i : cycleIdx)
            cycle.add(coinSymbols.get(i));
        cycle.add(coinSymbols.get(cycleIdx.get(0)));
        return cycle;
    }


    /**
     * Get unique sets of triplets of all coins in portfolio - order is ignored
     */
    private List<List<String>> generateTripletCombinations(List<String> list) {
        // Coins stored in the HashSet avoids duplicate combinations by storing each triplet in lexo. order
        Set<Set<String>> uniqueTripletSet = new HashSet<>();
        List<List<String>> combinations = new ArrayList<>();
        int n = list.size();
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    // TreeSet is used to sort elms in each triplet - to ensure uniqueness
                    Set<String> tripletSet = new TreeSet<>(List.of(list.get(i), list.get(j), list.get(k)));
                    if (uniqueTripletSet.add(tripletSet))
                        combinations.add(new ArrayList<>(tripletSet));
                }
            }
        }
        return combinations;
    }


    /**
     * Inverts a BigDecimal value
     */
    private BigDecimal invert(BigDecimal value) {
        if (value == null || BigDecimal.ZERO.compareTo(value) == 0) {
            System.out.println("Warning: Attempted to invert zero or null BigDecimal.");
            return BigDecimal.ONE;
        }
        return BigDecimal.ONE.divide(value, 10, RoundingMode.HALF_UP);
    }

}
