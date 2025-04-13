package com.adityapdev.ChaChing_api.controller;

import com.adityapdev.ChaChing_api.dto.coin.CoinAllDetailsDto;
import com.adityapdev.ChaChing_api.service.interfaces.ICoinService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coins")
public class CoinController {

    private ICoinService coinService;

    public CoinController(ICoinService coinService) {
        this.coinService = coinService;
    }

    @GetMapping
    public ResponseEntity<List<CoinAllDetailsDto>> getAllCoins() {
        List<CoinAllDetailsDto> coins = coinService.getAllCoins();
        return new ResponseEntity<>(coins, HttpStatus.OK);
    }

    @GetMapping("/{coinId}")
    public ResponseEntity<CoinAllDetailsDto> getCoinById(@PathVariable String coinId) {
        CoinAllDetailsDto coin = coinService.getCoinById(coinId);
        return new ResponseEntity<>(coin, HttpStatus.OK);
    }

    @DeleteMapping("/{coinId}")
    public ResponseEntity<String> deleteCoin(@PathVariable String coinId) {
        coinService.deleteCoin(coinId);
        return ResponseEntity.ok(String.format("Coin \"%s\" is deleted successfully.", coinId));
    }

}
