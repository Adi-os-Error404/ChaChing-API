package com.adityapdev.ChaChing_api.dto.coin;

import com.adityapdev.ChaChing_api.dto.comment.CommentDetailDto;

import java.math.BigDecimal;
import java.util.List;

public class CoinDetailDto extends CoinDto{

    public CoinDetailDto(String coinId, String symbol, String name, BigDecimal currentPrice, BigDecimal marketCap, List<CommentDetailDto> comments) {
        super(coinId, symbol, name, currentPrice, marketCap, comments);
    }

}
