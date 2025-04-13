package com.adityapdev.ChaChing_api.dto.portfolio;

import com.adityapdev.ChaChing_api.dto.coin.CoinDto;

import java.math.BigDecimal;

public class CoinInPortfolioDto extends CoinDto {

    public CoinInPortfolioDto(String coinId, String symbol, String name, BigDecimal currentPrice, Long marketCap) {
        super(coinId, symbol, name, currentPrice, marketCap);
    }

}
