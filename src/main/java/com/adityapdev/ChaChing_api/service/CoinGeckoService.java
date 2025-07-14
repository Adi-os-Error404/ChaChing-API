package com.adityapdev.ChaChing_api.service;

import com.adityapdev.ChaChing_api.dto.coin.CoinGeckoDto;
import com.adityapdev.ChaChing_api.entity.Coin;
import com.adityapdev.ChaChing_api.exception.ResourceNotFoundException;
import com.adityapdev.ChaChing_api.mapper.CoinMapper;
import com.adityapdev.ChaChing_api.service.interfaces.ICoinGeckoService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CoinGeckoService implements ICoinGeckoService {

    public final static String CURRENCY = "usd";
    private final static String GECKO_API = "https://api.coingecko.com/api/v3";
    private final RestTemplate restTemplate = new RestTemplate();

    // Cache duration: 5 minutes
    private static final long CACHE_DURATION_MILLIS = 5 * 60 * 1000;

    // Cache to store prices per coinId
    private final Map<String, CachedPrice> priceCache = new ConcurrentHashMap<>();

    @Override
    public Coin getCoinByCoinId(String coinId) {
        String coinDetailsApi = GECKO_API + "/coins/" + coinId;

        try {
            CoinGeckoDto details = restTemplate.getForObject(coinDetailsApi, CoinGeckoDto.class);
            return CoinMapper.mapToCoin(details, CURRENCY);

        } catch (RestClientException ex) {
            throw new ResourceNotFoundException(coinId + " does not exist.");
        }
    }

    @Override
    public BigDecimal getCurrentPrice(String coinId) {
        CachedPrice cachedPrice = priceCache.get(coinId);

        // Return cached price if not expired
        if (cachedPrice != null && (Instant.now().toEpochMilli() - cachedPrice.timestamp) < CACHE_DURATION_MILLIS) {
            return cachedPrice.price;
        }

        String currentPriceApi = GECKO_API + "/simple/price?ids=" + coinId + "&vs_currencies=" + CURRENCY;
        try {
            Map<String, Map<String, Number>> response = restTemplate.getForObject(currentPriceApi, Map.class);

            if (response != null && response.containsKey(coinId)) {
                Number priceNumber = response.get(coinId).get(CURRENCY);
                BigDecimal price = new BigDecimal(priceNumber.toString());

                // Update cache
                priceCache.put(coinId, new CachedPrice(price, Instant.now().toEpochMilli()));

                return price;
            } else {
                throw new ResourceNotFoundException(coinId + " price could not be found.");
            }
        } catch (RestClientException ex) {
            throw new ResourceNotFoundException("Error fetching price for " + coinId);
        }
    }

    // Inner class for cached price data
    private static class CachedPrice {
        final BigDecimal price;
        final long timestamp;

        CachedPrice(BigDecimal price, long timestamp) {
            this.price = price;
            this.timestamp = timestamp;
        }
    }
}
