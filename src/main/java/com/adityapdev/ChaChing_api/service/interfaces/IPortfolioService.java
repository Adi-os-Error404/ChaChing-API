package com.adityapdev.ChaChing_api.service.interfaces;

import com.adityapdev.ChaChing_api.dto.portfolio.CoinInPortfolioDto;

import java.util.List;

public interface IPortfolioService {
    List<CoinInPortfolioDto> getCurrentUserCoins();
    CoinInPortfolioDto addCoinToUserPort(String coinId);

}
