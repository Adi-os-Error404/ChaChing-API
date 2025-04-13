package com.adityapdev.ChaChing_api.dto.coin;

import com.adityapdev.ChaChing_api.dto.comment.CommentDetailDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class CoinAllDetailsDto extends CoinGeckoDto {

    public List<CommentDetailDto> comments;

    public CoinAllDetailsDto(
            String id,
            String name,
            String symbol,
            Image image,
            Integer market_cap_rank,
            String genesis_date,
            BigDecimal sentiment_votes_up_percentage,
            BigDecimal sentiment_votes_down_percentage,
            Long watchlist_portfolio_users,
            MarketData market_data,
            List<CommentDetailDto> comments
    ) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.image = image;
        this.market_cap_rank = market_cap_rank;
        this.genesis_date = genesis_date;
        this.sentiment_votes_up_percentage = sentiment_votes_up_percentage;
        this.sentiment_votes_down_percentage = sentiment_votes_down_percentage;
        this.watchlist_portfolio_users = watchlist_portfolio_users;
        this.market_data = market_data;
        this.comments = comments;
    }

    public List<CommentDetailDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentDetailDto> comments) {
        this.comments = comments;
    }
}
