package com.adityapdev.ChaChing_api.dto.portfolio;

import java.math.BigDecimal;

public class CoinInPortfolioDto {

    public String coinId;
    public String symbol;
    public String name;
    public String imgUrl;

    public CoinInPortfolioDto(String coinId, String symbol, String name, String imgUrl) {
        this.coinId = coinId;
        this.symbol = symbol;
        this.name = name;
        this.imgUrl = imgUrl;
    }

}
