package com.adityapdev.ChaChing_api.service.interfaces;

import com.adityapdev.ChaChing_api.dto.coin.CoinAllDetailsDto;
import com.adityapdev.ChaChing_api.entity.Coin;

import java.util.List;

public interface ICoinService {
    List<CoinAllDetailsDto> getAllCoins();
    CoinAllDetailsDto getCoinById(String coinId);
    void deleteCoin(String coinId);
    Coin findCoinById(String coinId);
}
