package com.adityapdev.ChaChing_api.service.interfaces;

import com.adityapdev.ChaChing_api.dto.coin.AddCoinDto;
import com.adityapdev.ChaChing_api.dto.coin.CoinDetailDto;

import java.math.BigDecimal;
import java.util.List;

public interface ICoinService {
    CoinDetailDto addCoin(AddCoinDto addCoinDto);
    List<CoinDetailDto> getAllCoins();
    CoinDetailDto getCoinById(String coinId);
    CoinDetailDto updateCurrentPrice(String coinId, BigDecimal currentPriceUsd);
    void deleteCoin(String coinId);
}
