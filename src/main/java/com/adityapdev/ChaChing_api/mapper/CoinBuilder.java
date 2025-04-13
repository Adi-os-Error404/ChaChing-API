package com.adityapdev.ChaChing_api.mapper;

import com.adityapdev.ChaChing_api.dto.coin.CoinAllDetailsDto;
import com.adityapdev.ChaChing_api.dto.coin.CoinGeckoDto;
import com.adityapdev.ChaChing_api.dto.comment.CommentDetailDto;
import com.adityapdev.ChaChing_api.entity.Coin;
import com.adityapdev.ChaChing_api.service.CoinGeckoService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class CoinBuilder {
    private String coinId;
    private String symbol;
    private String name;
    private BigDecimal currentPrice;
    private Long marketCap;
    private Integer marketCapRank;
    private String genesisDate;
    private BigDecimal sentimentVotesUpPercentage;
    private BigDecimal sentimentVotesDownPercentage;
    private Long watchlistPortfolioUsers;
    private String imageThumb;
    private String imageSmall;
    private String imageLarge;
    private BigDecimal ath;
    private BigDecimal atl;
    private BigDecimal high24h;
    private BigDecimal low24h;
    private BigDecimal totalSupply;
    private BigDecimal maxSupply;
    private BigDecimal circulatingSupply;
    private List<CommentDetailDto> commentDtos;
    private String curr =CoinGeckoService.CURRENCY;

    // CoinBuilder setters for chaining
    public CoinBuilder setCoinId(String coinId) {
        this.coinId = coinId;
        return this;
    }

    public CoinBuilder setSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public CoinBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public CoinBuilder setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
        return this;
    }

    public CoinBuilder setMarketCap(Long marketCap) {
        this.marketCap = marketCap;
        return this;
    }

    public CoinBuilder setMarketCapRank(Integer marketCapRank) {
        this.marketCapRank = marketCapRank;
        return this;
    }

    public CoinBuilder setGenesisDate(String genesisDate) {
        this.genesisDate = genesisDate;
        return this;
    }

    public CoinBuilder setSentimentVotesUpPercentage(BigDecimal sentimentVotesUpPercentage) {
        this.sentimentVotesUpPercentage = sentimentVotesUpPercentage;
        return this;
    }

    public CoinBuilder setSentimentVotesDownPercentage(BigDecimal sentimentVotesDownPercentage) {
        this.sentimentVotesDownPercentage = sentimentVotesDownPercentage;
        return this;
    }

    public CoinBuilder setWatchlistPortfolioUsers(Long watchlistPortfolioUsers) {
        this.watchlistPortfolioUsers = watchlistPortfolioUsers;
        return this;
    }

    public CoinBuilder setImageThumb(String imageThumb) {
        this.imageThumb = imageThumb;
        return this;
    }

    public CoinBuilder setImageSmall(String imageSmall) {
        this.imageSmall = imageSmall;
        return this;
    }

    public CoinBuilder setImageLarge(String imageLarge) {
        this.imageLarge = imageLarge;
        return this;
    }

    public CoinBuilder setAth(BigDecimal ath) {
        this.ath = ath;
        return this;
    }

    public CoinBuilder setAtl(BigDecimal atl) {
        this.atl = atl;
        return this;
    }

    public CoinBuilder setHigh24h(BigDecimal high24h) {
        this.high24h = high24h;
        return this;
    }

    public CoinBuilder setLow24h(BigDecimal low24h) {
        this.low24h = low24h;
        return this;
    }

    public CoinBuilder setTotalSupply(BigDecimal totalSupply) {
        this.totalSupply = totalSupply;
        return this;
    }

    public CoinBuilder setMaxSupply(BigDecimal maxSupply) {
        this.maxSupply = maxSupply;
        return this;
    }

    public CoinBuilder setCirculatingSupply(BigDecimal circulatingSupply) {
        this.circulatingSupply = circulatingSupply;
        return this;
    }

    public CoinBuilder setCommentDtos(List<CommentDetailDto> commentDtos) {
        this.commentDtos = commentDtos;
        return this;
    }

    // Build Coin entity
    public Coin buildCoin() {
        return new Coin(coinId, symbol, name, currentPrice, marketCap, marketCapRank, genesisDate,
                sentimentVotesUpPercentage, sentimentVotesDownPercentage, watchlistPortfolioUsers,
                imageThumb, imageSmall, imageLarge, ath, atl, high24h, low24h, totalSupply, maxSupply, circulatingSupply);
    }

    // Build CoinAllDetailsDto
    public CoinAllDetailsDto buildCoinAllDetailsDto() {
        return new CoinAllDetailsDto(coinId, name, symbol, createImage(), marketCapRank, genesisDate,
                sentimentVotesUpPercentage, sentimentVotesDownPercentage, watchlistPortfolioUsers,
                createMarketData(), commentDtos);
    }

    // Helpers:
    private CoinGeckoDto.Image createImage() {
        CoinGeckoDto.Image image = new CoinGeckoDto.Image();
        image.thumb = this.imageThumb;
        image.small = this.imageSmall;
        image.large = this.imageLarge;
        return image;
    }

    private CoinGeckoDto.MarketData createMarketData() {
        CoinGeckoDto.MarketData marketData = new CoinGeckoDto.MarketData();

        marketData.current_price = Map.of("usd", this.currentPrice);
        marketData.market_cap = Map.of(curr, this.marketCap);
        marketData.ath = Map.of(curr, this.ath);
        marketData.atl = Map.of(curr, this.atl);
        marketData.high_24h = Map.of(curr, this.high24h);
        marketData.low_24h = Map.of(curr, this.low24h);
        marketData.total_supply = this.totalSupply;
        marketData.max_supply = this.maxSupply;
        marketData.circulating_supply = this.circulatingSupply;
        return marketData;
    }

}
