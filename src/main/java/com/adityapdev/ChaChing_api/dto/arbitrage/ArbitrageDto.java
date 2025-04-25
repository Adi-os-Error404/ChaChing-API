package com.adityapdev.ChaChing_api.dto.arbitrage;

import java.math.BigDecimal;
import java.util.List;

public class ArbitrageDto {

    private List<TradeStep> tradeSteps;
    private BigDecimal rateProduct;
    private BigDecimal profitPercentage;

    public ArbitrageDto() {}
    public ArbitrageDto(List<TradeStep> tradeSteps, BigDecimal rateProduct, BigDecimal profitPercentage) {
        this.tradeSteps = tradeSteps;
        this.rateProduct = rateProduct;
        this.profitPercentage = profitPercentage;
    }

    public List<TradeStep> getTradeSteps() {
        return tradeSteps;
    }

    public void setTradeSteps(List<TradeStep> tradeSteps) {
        this.tradeSteps = tradeSteps;
    }

    public BigDecimal getRateProduct() {
        return rateProduct;
    }

    public void setRateProduct(BigDecimal rateProduct) {
        this.rateProduct = rateProduct;
    }

    public BigDecimal getProfitPercentage() {
        return profitPercentage;
    }

    public void setProfitPercentage(BigDecimal profitPercentage) {
        this.profitPercentage = profitPercentage;
    }


    // TradeStep class:
    public static class TradeStep {
        private String from;
        private String to;
        private BigDecimal rate;
        private String fromImg;

        public TradeStep() {
        }

        public TradeStep(String from, String to, BigDecimal rate, String fromImg) {
            this.from = from;
            this.to = to;
            this.rate = rate;
            this.fromImg = fromImg;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public BigDecimal getRate() {
            return rate;
        }

        public void setRate(BigDecimal rate) {
            this.rate = rate;
        }

        public String getFromImg() {
            return fromImg;
        }

        public void setFromImg(String fromImg) {
            this.fromImg = fromImg;
        }

    }

}
