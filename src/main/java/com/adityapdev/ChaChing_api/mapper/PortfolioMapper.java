package com.adityapdev.ChaChing_api.mapper;

import com.adityapdev.ChaChing_api.dto.portfolio.CoinInPortfolioDto;
import com.adityapdev.ChaChing_api.entity.Coin;

public class PortfolioMapper {

    public static CoinInPortfolioDto mapToPortfolioDto(Coin coin) {
        return new CoinInPortfolioDto(
                coin.getCoinId(),
                coin.getSymbol(),
                coin.getName(),
                coin.getImageLarge()
        );
    }

}
