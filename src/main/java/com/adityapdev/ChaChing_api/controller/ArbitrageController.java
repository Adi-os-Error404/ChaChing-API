package com.adityapdev.ChaChing_api.controller;

import com.adityapdev.ChaChing_api.dto.arbitrage.ArbitrageDto;
import com.adityapdev.ChaChing_api.service.interfaces.IArbitrageService;
import com.adityapdev.ChaChing_api.service.interfaces.IPortfolioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/arbitrage")
public class ArbitrageController {

    private final IArbitrageService arbitrageService;
    private final IPortfolioService portfolioService;

    public ArbitrageController(IArbitrageService arbitrageService, IPortfolioService portfolioService) {
        this.arbitrageService = arbitrageService;
        this.portfolioService = portfolioService;
    }

    @GetMapping
    public ResponseEntity<List<ArbitrageDto>> detectArbitrages(@RequestParam BigDecimal profitMargin) {
        List<String> coinSymbols = portfolioService.getCurrentUserCoins().stream().map(c -> c.symbol).toList();
        List<ArbitrageDto> opportunities = arbitrageService.detectArbitrage(profitMargin, coinSymbols);
        return ResponseEntity.ok(opportunities);
    }
}