package com.adityapdev.ChaChing_api.controller;

import com.adityapdev.ChaChing_api.dto.portfolio.CoinInPortfolioDto;
import com.adityapdev.ChaChing_api.service.interfaces.IPortfolioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/portfolio")
public class PortfolioController {

    private IPortfolioService portfolioService;

    public PortfolioController(IPortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @GetMapping
    public ResponseEntity<List<CoinInPortfolioDto>> getPortfolio() {
        List<CoinInPortfolioDto> userCoins = portfolioService.getCurrentUserCoins();
        return new ResponseEntity<>(userCoins, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CoinInPortfolioDto> addCoinToPortfolio(@RequestParam String coinId) {
        CoinInPortfolioDto addedCoin = portfolioService.addCoinToUserPort(coinId);
        return new ResponseEntity<>(addedCoin, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> removeCoinFromPortfolio(@RequestParam String coinId) {
        String res = portfolioService.removeCoinFromUserPort(coinId);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

}
