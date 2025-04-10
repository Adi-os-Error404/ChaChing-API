package com.adityapdev.ChaChing_api.dto.coin;


import com.adityapdev.ChaChing_api.dto.comment.CommentDetailDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public abstract class CoinDto {
    private String coinId;
    private String symbol;
    private String name;
    private BigDecimal currentPrice;
    private BigDecimal marketCap;
    private List<CommentDetailDto> comments;

    public CoinDto(String coinId, String symbol, String name, BigDecimal currentPrice, BigDecimal marketCap, List<CommentDetailDto> comments) {
        this.coinId = coinId;
        this.symbol = symbol;
        this.name = name;
        this.currentPrice = currentPrice;
        this.marketCap = marketCap;
        this.comments = (comments != null) ? comments : new ArrayList<>();
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

    public BigDecimal getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(BigDecimal marketCap) {
        this.marketCap = marketCap;
    }

    public List<CommentDetailDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentDetailDto> comments) {
        this.comments = comments;
    }
}
