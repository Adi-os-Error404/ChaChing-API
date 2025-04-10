package com.adityapdev.ChaChing_api.mapper;

import com.adityapdev.ChaChing_api.dto.coin.AddCoinDto;
import com.adityapdev.ChaChing_api.dto.coin.CoinDetailDto;
import com.adityapdev.ChaChing_api.dto.comment.CommentDetailDto;
import com.adityapdev.ChaChing_api.entity.Coin;

import java.util.List;
import java.util.stream.Collectors;

public class CoinMapper {

    public static CoinDetailDto mapToCoinDto(Coin coin) {
        List<CommentDetailDto> commentDtos = coin.getComments().stream()
                .map(e -> CommentMapper.mapToCommentDto(e, coin.getCoinId()))
                .collect(Collectors.toList());

        return new CoinDetailDto(
                coin.getCoinId(),
                coin.getSymbol(),
                coin.getName(),
                coin.getCurrentPrice(),
                coin.getMarketCap(),
                commentDtos
        );
    }

    public static Coin mapToCoin(AddCoinDto addCoinDto) {
        return new Coin(
                addCoinDto.getCoinId(),
                addCoinDto.getSymbol(),
                addCoinDto.getName(),
                addCoinDto.getCurrentPrice(),
                addCoinDto.getMarketCap()
        );
    }

}
