package com.adityapdev.ChaChing_api.entity;

import java.math.BigDecimal;
import java.util.List;

public class CurrencyGraph {
    private final List<String> currencies;
    private final BigDecimal[][] matrix;
    private final BigDecimal[][] rates;
    private final int[][] successor;

    public CurrencyGraph(List<String> currencies) {
        this.currencies = currencies;
        int n = currencies.size();
        this.matrix = new BigDecimal[n][n];
        this.rates = new BigDecimal[n][n];
        this.successor = new int[n][n];
    }

    public List<String> getCurrencies() {
        return currencies;
    }

    public BigDecimal[][] getMatrix() {
        return matrix;
    }

    public BigDecimal[][] getRates() {
        return rates;
    }

    public int[][] getSuccessor() {
        return successor;
    }

    public void setRate(int i, int j, BigDecimal rate) {
        this.matrix[i][j] = rate;
        this.rates[i][j] = rate;
        this.successor[i][j] = j;
    }

    public void displayMatrix(BigDecimal[][] matrix, String title) {
        System.out.println("\n=== " + title + " ===");
        System.out.print("     ");
        for (String currency : currencies) {
            System.out.printf("%10s", currency);
        }
        System.out.println();
        for (int i = 0; i < currencies.size(); i++) {
            System.out.printf("%5s", currencies.get(i));
            for (int j = 0; j < currencies.size(); j++) {
                System.out.printf("%10.3f", matrix[i][j]);
            }
            System.out.println();
        }
    }
}
