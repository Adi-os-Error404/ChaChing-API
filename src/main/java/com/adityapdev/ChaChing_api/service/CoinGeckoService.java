package com.adityapdev.ChaChing_api.service;

import com.adityapdev.ChaChing_api.dto.coin.CoinGeckoDto;
import com.adityapdev.ChaChing_api.entity.Coin;
import com.adityapdev.ChaChing_api.exception.ResourceNotFoundException;
import com.adityapdev.ChaChing_api.service.interfaces.ICoinGeckoService;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class CoinGeckoService implements ICoinGeckoService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final static String currency = "usd";

    @Override
    public Coin getCoinByCoinId(String coinId) {
        String coinDetailsApi = "https://api.coingecko.com/api/v3/coins/" + coinId;

        try {
            CoinGeckoDto details = restTemplate.getForObject(coinDetailsApi, CoinGeckoDto.class);
            return new Coin(
                    details.id,
                    details.symbol,
                    details.name,
                    details.market_data.current_price.get(currency),
                    details.market_data.market_cap.get(currency)
            );
        } catch (RestClientException ex) {
            throw new ResourceNotFoundException(coinId + "does not exist.");
        }
    }

}
