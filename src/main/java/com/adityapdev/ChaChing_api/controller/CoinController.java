package com.adityapdev.ChaChing_api.controller;

import com.adityapdev.ChaChing_api.dto.coin.AddCoinDto;
import com.adityapdev.ChaChing_api.dto.coin.CoinCommentDetailDto;
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
    public ResponseEntity<CoinCommentDetailDto> addCoin(@RequestBody AddCoinDto addCoinDto) {
        CoinCommentDetailDto createdCoin = coinService.addCoin(addCoinDto);
        return new ResponseEntity<>(createdCoin, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CoinCommentDetailDto>> getAllCoins() {
        List<CoinCommentDetailDto> coins = coinService.getAllCoins();
        return new ResponseEntity<>(coins, HttpStatus.OK);
    }

    @GetMapping("/{coinId}")
    public ResponseEntity<CoinCommentDetailDto> getCoinById(@PathVariable String coinId) {
        CoinCommentDetailDto coin = coinService.getCoinById(coinId);
        return new ResponseEntity<>(coin, HttpStatus.OK);
    }

    @PutMapping("/{coinId}/price")
    public ResponseEntity<CoinCommentDetailDto> updateCurrentPrice(@PathVariable String coinId, @RequestBody BigDecimal currentPriceUsd) {
        CoinCommentDetailDto updatedCoin = coinService.updateCurrentPrice(coinId, currentPriceUsd);
        return new ResponseEntity<>(updatedCoin, HttpStatus.OK);
    }

    @DeleteMapping("/{coinId}")
    public ResponseEntity<String> deleteCoin(@PathVariable String coinId) {
        coinService.deleteCoin(coinId);
        return ResponseEntity.ok(String.format("Coin \"%s\" is deleted successfully.", coinId));
    }

}
