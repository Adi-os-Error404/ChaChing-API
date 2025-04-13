package com.adityapdev.ChaChing_api.dto.coin;

import com.adityapdev.ChaChing_api.dto.comment.CommentDetailDto;

import java.math.BigDecimal;
import java.util.List;

public class CoinCommentDto extends CoinDto{

    private List<CommentDetailDto> comments;

    public CoinCommentDto(String coinId, String symbol, String name, BigDecimal currentPrice, Long marketCap, List<CommentDetailDto> comments) {
        super(coinId, symbol, name, currentPrice, marketCap);
        this.comments = comments;
    }

    public List<CommentDetailDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentDetailDto> comments) {
        this.comments = comments;
    }

}
