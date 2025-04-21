package com.adityapdev.ChaChing_api.service;

import com.adityapdev.ChaChing_api.dto.coin.CoinGeckoDto;
import com.adityapdev.ChaChing_api.entity.Coin;
import com.adityapdev.ChaChing_api.exception.ResourceNotFoundException;
import com.adityapdev.ChaChing_api.mapper.CoinMapper;
import com.adityapdev.ChaChing_api.service.interfaces.ICoinGeckoService;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

public class CoinGeckoService implements ICoinGeckoService {

    public final static String CURRENCY = "usd";
    private final static String GECKO_API = "https://api.coingecko.com/api/v3";
    private final RestTemplate restTemplate = new RestTemplate();

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
        String currentPriceApi = GECKO_API + "/simple/price?ids=" + coinId + "&vs_currencies=" + CURRENCY;
        try {
            Map<String, Map<String, Number>> response = restTemplate.getForObject(currentPriceApi, Map.class);

            if (response != null && response.containsKey(coinId)) {
                Number priceNumber = response.get(coinId).get(CURRENCY);
                return new BigDecimal(priceNumber.toString());
            } else {
                throw new ResourceNotFoundException(coinId + " price could not be found.");
            }
        } catch (RestClientException ex) {
            throw new ResourceNotFoundException("Error fetching price for " + coinId);
        }

    }

}
