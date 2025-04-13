package com.adityapdev.ChaChing_api.dto.coin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinGeckoDto {
    public String id;
    public String name;
    public String symbol;
    public Image image;
    public Integer market_cap_rank;
    public String genesis_date;
    public BigDecimal sentiment_votes_up_percentage;
    public BigDecimal sentiment_votes_down_percentage;
    public Long watchlist_portfolio_users;
    public MarketData market_data;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Image {
        public String thumb;
        public String small;
        public String large;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MarketData {
        public Map<String, BigDecimal> current_price;
        public Map<String, BigDecimal> ath;
        public Map<String, BigDecimal> atl;
        public Map<String, BigDecimal> high_24h;
        public Map<String, BigDecimal> low_24h;
        public Map<String, Long> market_cap;
        public BigDecimal total_supply;
        public BigDecimal max_supply;
        public BigDecimal circulating_supply;
    }
}
