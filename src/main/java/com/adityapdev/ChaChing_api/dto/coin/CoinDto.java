package com.adityapdev.ChaChing_api.dto.coin;


import com.adityapdev.ChaChing_api.dto.comment.CommentDetailDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


// TODO: Remove this - make a smaller quick dto - only need name, symbol, pic
public abstract class CoinDto {
    private String coinId;
    private String symbol;
    private String name;
    private BigDecimal currentPrice;
    private Long marketCap;

    public CoinDto(String coinId, String symbol, String name, BigDecimal currentPrice, Long marketCap) {
        this.coinId = coinId;
        this.symbol = symbol;
        this.name = name;
        this.currentPrice = currentPrice;
        this.marketCap = marketCap;
    }

    public String getCoinId() {
        return coinId;
    }

    public void setCoinId(String coinId) {
        this.coinId = coinId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Long getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(Long marketCap) {
        this.marketCap = marketCap;
    }

}
