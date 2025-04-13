package com.adityapdev.ChaChing_api.mapper;

import com.adityapdev.ChaChing_api.dto.coin.CoinAllDetailsDto;
import com.adityapdev.ChaChing_api.dto.coin.CoinGeckoDto;
import com.adityapdev.ChaChing_api.dto.comment.CommentDetailDto;
import com.adityapdev.ChaChing_api.entity.Coin;

import java.util.List;
import java.util.stream.Collectors;

public class CoinMapper {

    public static CoinAllDetailsDto mapToCoinDto(Coin coin) {
        List<CommentDetailDto> commentDtos = coin.getComments().stream()
                .map(CommentMapper::mapToCommentDto)
                .collect(Collectors.toList());

        return new CoinBuilder()
                .setCoinId(coin.getCoinId())
                .setSymbol(coin.getSymbol())
                .setName(coin.getName())
                .setCurrentPrice(coin.getCurrentPrice())
                .setImageThumb(coin.getImageThumb())
                .setImageSmall(coin.getImageSmall())
                .setImageLarge(coin.getImageLarge())
                .setMarketCap(coin.getMarketCap())
                .setMarketCapRank(coin.getMarketCapRank())
                .setGenesisDate(coin.getGenesisDate())
                .setSentimentVotesUpPercentage(coin.getSentimentVotesUpPercentage())
                .setSentimentVotesDownPercentage(coin.getSentimentVotesDownPercentage())
                .setWatchlistPortfolioUsers(coin.getWatchlistPortfolioUsers())
                .setAth(coin.getAth())
                .setAtl(coin.getAtl())
                .setHigh24h(coin.getHigh24h())
                .setLow24h(coin.getLow24h())
                .setTotalSupply(coin.getTotalSupply())
                .setMaxSupply(coin.getMaxSupply())
                .setCirculatingSupply(coin.getCirculatingSupply())
                .setCommentDtos(commentDtos)
                .buildCoinAllDetailsDto();
    }

    public static Coin mapToCoin(CoinGeckoDto details, String currency) {
        return new CoinBuilder()
                .setCoinId(details.id)
                .setSymbol(details.symbol)
                .setName(details.name)
                .setCurrentPrice(details.market_data.current_price.get(currency))
                .setMarketCap(details.market_data.market_cap.get(currency))
                .setMarketCapRank(details.market_cap_rank)
                .setGenesisDate(details.genesis_date)
                .setSentimentVotesUpPercentage(details.sentiment_votes_up_percentage)
                .setSentimentVotesDownPercentage(details.sentiment_votes_down_percentage)
                .setWatchlistPortfolioUsers(details.watchlist_portfolio_users)
                .setImageThumb(details.image.thumb)
                .setImageSmall(details.image.small)
                .setImageLarge(details.image.large)
                .setAth(details.market_data.ath.get(currency))
                .setAtl(details.market_data.atl.get(currency))
                .setHigh24h(details.market_data.high_24h.get(currency))
                .setLow24h(details.market_data.low_24h.get(currency))
                .setTotalSupply(details.market_data.total_supply)
                .setMaxSupply(details.market_data.max_supply)
                .setCirculatingSupply(details.market_data.circulating_supply)
                .buildCoin();
    }

}
