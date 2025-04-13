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
    private BigDecimal marketCap;

    @OneToMany(mappedBy = "coin", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    public Coin() {}

    public Coin(String coinId, String symbol, String name, BigDecimal currentPrice, BigDecimal marketCap) {
        this.coinId = coinId;
        this.symbol = symbol;
        this.name = name;
        this.currentPrice = currentPrice;
        this.marketCap = marketCap;
        this.comments = new ArrayList<>();
    }

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

    public BigDecimal getMarketCap() {
        return marketCap;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

}
