package com.adityapdev.ChaChing_api.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "coins")
public class Coin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "coinId", nullable = false, unique = true)
    private String coinId;

    @Column(name = "symbol", nullable = false, unique = true)
    private String symbol;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "current_price_usd")
    private BigDecimal currentPrice;

    @Column(name = "market_cap", nullable = false)
    private Long marketCap;

    @Column(name = "market_cap_rank")
    private Integer marketCapRank;

    @Column(name = "genesis_date")
    private String genesisDate;

    @Column(name = "sentiment_votes_up_percentage")
    private BigDecimal sentimentVotesUpPercentage;

    @Column(name = "sentiment_votes_down_percentage")
    private BigDecimal sentimentVotesDownPercentage;

    @Column(name = "watchlist_portfolio_users")
    private Long watchlistPortfolioUsers;

    @Column(name = "image_thumb")
    private String imageThumb;

    @Column(name = "image_small")
    private String imageSmall;

    @Column(name = "image_large")
    private String imageLarge;

    // New fields
    @Column(name = "ath_usd")
    private BigDecimal ath;

    @Column(name = "atl_usd")
    private BigDecimal atl;

    @Column(name = "high_24h_usd")
    private BigDecimal high24h;

    @Column(name = "low_24h_usd")
    private BigDecimal low24h;

    @Column(name = "total_supply")
    private BigDecimal totalSupply;

    @Column(name = "max_supply")
    private BigDecimal maxSupply;

    @Column(name = "circulating_supply")
    private BigDecimal circulatingSupply;

    @OneToMany(mappedBy = "coin", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    public Coin() {
        this.comments = new ArrayList<>();
    }

    public Coin(String coinId, String symbol, String name, BigDecimal currentPrice, Long marketCap,
                Integer marketCapRank, String genesisDate, BigDecimal sentimentVotesUpPercentage,
                BigDecimal sentimentVotesDownPercentage, Long watchlistPortfolioUsers,
                String imageThumb, String imageSmall, String imageLarge, BigDecimal ath, BigDecimal atl,
                BigDecimal high24h, BigDecimal low24h, BigDecimal totalSupply, BigDecimal maxSupply,
                BigDecimal circulatingSupply) {
        this.coinId = coinId;
        this.symbol = symbol;
        this.name = name;
        this.currentPrice = currentPrice;
        this.marketCap = marketCap;
        this.marketCapRank = marketCapRank;
        this.genesisDate = genesisDate;
        this.sentimentVotesUpPercentage = sentimentVotesUpPercentage;
        this.sentimentVotesDownPercentage = sentimentVotesDownPercentage;
        this.watchlistPortfolioUsers = watchlistPortfolioUsers;
        this.imageThumb = imageThumb;
        this.imageSmall = imageSmall;
        this.imageLarge = imageLarge;
        this.ath = ath;
        this.atl = atl;
        this.high24h = high24h;
        this.low24h = low24h;
        this.totalSupply = totalSupply;
        this.maxSupply = maxSupply;
        this.circulatingSupply = circulatingSupply;
        this.comments = new ArrayList<>();
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public String getCoinId() {
        return coinId;
    }

    public String getSymbol() {
        return symbol;
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

    public Integer getMarketCapRank() {
        return marketCapRank;
    }

    public void setMarketCapRank(Integer marketCapRank) {
        this.marketCapRank = marketCapRank;
    }

    public String getGenesisDate() {
        return genesisDate;
    }

    public void setGenesisDate(String genesisDate) {
        this.genesisDate = genesisDate;
    }

    public BigDecimal getSentimentVotesUpPercentage() {
        return sentimentVotesUpPercentage;
    }

    public void setSentimentVotesUpPercentage(BigDecimal sentimentVotesUpPercentage) {
        this.sentimentVotesUpPercentage = sentimentVotesUpPercentage;
    }

    public BigDecimal getSentimentVotesDownPercentage() {
        return sentimentVotesDownPercentage;
    }

    public void setSentimentVotesDownPercentage(BigDecimal sentimentVotesDownPercentage) {
        this.sentimentVotesDownPercentage = sentimentVotesDownPercentage;
    }

    public Long getWatchlistPortfolioUsers() {
        return watchlistPortfolioUsers;
    }

    public void setWatchlistPortfolioUsers(Long watchlistPortfolioUsers) {
        this.watchlistPortfolioUsers = watchlistPortfolioUsers;
    }

    public String getImageThumb() {
        return imageThumb;
    }

    public void setImageThumb(String imageThumb) {
        this.imageThumb = imageThumb;
    }

    public String getImageSmall() {
        return imageSmall;
    }

    public void setImageSmall(String imageSmall) {
        this.imageSmall = imageSmall;
    }

    public String getImageLarge() {
        return imageLarge;
    }

    public void setImageLarge(String imageLarge) {
        this.imageLarge = imageLarge;
    }

    public BigDecimal getAth() {
        return ath;
    }

    public void setAth(BigDecimal ath) {
        this.ath = ath;
    }

    public BigDecimal getAtl() {
        return atl;
    }

    public void setAtl(BigDecimal atl) {
        this.atl = atl;
    }

    public BigDecimal getHigh24h() {
        return high24h;
    }

    public void setHigh24h(BigDecimal high24h) {
        this.high24h = high24h;
    }

    public BigDecimal getLow24h() {
        return low24h;
    }

    public void setLow24h(BigDecimal low24h) {
        this.low24h = low24h;
    }

    public BigDecimal getTotalSupply() {
        return totalSupply;
    }

    public void setTotalSupply(BigDecimal totalSupply) {
        this.totalSupply = totalSupply;
    }

    public BigDecimal getMaxSupply() {
        return maxSupply;
    }

    public void setMaxSupply(BigDecimal maxSupply) {
        this.maxSupply = maxSupply;
    }

    public BigDecimal getCirculatingSupply() {
        return circulatingSupply;
    }

    public void setCirculatingSupply(BigDecimal circulatingSupply) {
        this.circulatingSupply = circulatingSupply;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }
}
