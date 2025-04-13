package com.adityapdev.ChaChing_api.service.interfaces;

import com.adityapdev.ChaChing_api.entity.Coin;

public interface ICoinGeckoService {
    Coin getCoinByCoinId(String coindId);
}
