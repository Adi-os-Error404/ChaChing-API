package com.adityapdev.ChaChing_api.service.interfaces;

import com.adityapdev.ChaChing_api.entity.Coin;

import java.math.BigDecimal;

public interface ICoinGeckoService {
    Coin getCoinByCoinId(String coindId);
    BigDecimal getCurrentPrice(String coinId);
}
