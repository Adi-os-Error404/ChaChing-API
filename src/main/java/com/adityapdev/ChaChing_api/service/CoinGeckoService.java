package com.adityapdev.ChaChing_api.service;

import com.adityapdev.ChaChing_api.dto.coin.CoinGeckoDto;
import com.adityapdev.ChaChing_api.entity.Coin;
import com.adityapdev.ChaChing_api.exception.ResourceNotFoundException;
import com.adityapdev.ChaChing_api.mapper.CoinMapper;
import com.adityapdev.ChaChing_api.service.interfaces.ICoinGeckoService;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class CoinGeckoService implements ICoinGeckoService {

    public final static String CURRENCY = "usd";
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public Coin getCoinByCoinId(String coinId) {
        String coinDetailsApi = "https://api.coingecko.com/api/v3/coins/" + coinId;

        try {
            CoinGeckoDto details = restTemplate.getForObject(coinDetailsApi, CoinGeckoDto.class);
            return CoinMapper.mapToCoin(details, CURRENCY);

        } catch (RestClientException ex) {
            throw new ResourceNotFoundException(coinId + "does not exist.");
        }
    }

}
