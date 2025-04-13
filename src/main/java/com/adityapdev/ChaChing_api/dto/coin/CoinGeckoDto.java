package com.adityapdev.ChaChing_api.dto.coin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinGeckoDto {
    public String id;
    public String name;
    public String symbol;
    public MarketData market_data;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MarketData {
        public Map<String, BigDecimal> current_price;
        public Map<String, Long> market_cap;
    }
}
