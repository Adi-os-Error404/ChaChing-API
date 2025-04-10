package com.adityapdev.ChaChing_api.controller;

import com.adityapdev.ChaChing_api.dto.coin.AddCoinDto;
import com.adityapdev.ChaChing_api.dto.coin.CoinDetailDto;
import com.adityapdev.ChaChing_api.service.interfaces.ICoinService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/coins")
public class CoinController {

    private ICoinService coinService;

    public CoinController(ICoinService coinService) {
        this.coinService = coinService;
    }

    @PostMapping
    public ResponseEntity<CoinDetailDto> addCoin(@RequestBody AddCoinDto addCoinDto) {
        CoinDetailDto createdCoin = coinService.addCoin(addCoinDto);
        return new ResponseEntity<>(createdCoin, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CoinDetailDto>> getAllCoins() {
        List<CoinDetailDto> coins = coinService.getAllCoins();
        return new ResponseEntity<>(coins, HttpStatus.OK);
    }

    @GetMapping("/{coinId}")
    public ResponseEntity<CoinDetailDto> getCoinById(@PathVariable String coinId) {
        CoinDetailDto coin = coinService.getCoinById(coinId);
        return new ResponseEntity<>(coin, HttpStatus.OK);
    }

    @PutMapping("/{coinId}/price")
    public ResponseEntity<CoinDetailDto> updateCurrentPrice(@PathVariable String coinId, @RequestBody BigDecimal currentPriceUsd) {
        CoinDetailDto updatedCoin = coinService.updateCurrentPrice(coinId, currentPriceUsd);
        return new ResponseEntity<>(updatedCoin, HttpStatus.OK);
    }

    @DeleteMapping("/{coinId}")
    public ResponseEntity<String> deleteCoin(@PathVariable String coinId) {
        coinService.deleteCoin(coinId);
        return ResponseEntity.ok(String.format("Coin \"%s\" is deleted successfully.", coinId));
    }

}
