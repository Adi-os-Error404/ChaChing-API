package com.adityapdev.ChaChing_api.service.interfaces;

import com.adityapdev.ChaChing_api.dto.coin.AddCoinDto;
import com.adityapdev.ChaChing_api.dto.coin.CoinCommentDetailDto;
import com.adityapdev.ChaChing_api.entity.Coin;

import java.math.BigDecimal;
import java.util.List;

public interface ICoinService {
    CoinCommentDetailDto addCoin(AddCoinDto addCoinDto);
    List<CoinCommentDetailDto> getAllCoins();
    CoinCommentDetailDto getCoinById(String coinId);
    CoinCommentDetailDto updateCurrentPrice(String coinId, BigDecimal currentPriceUsd);
    void deleteCoin(String coinId);
    Coin findCoinById(String coinId);
}
