package com.adityapdev.ChaChing_api.mapper;

import com.adityapdev.ChaChing_api.dto.comment.AddCommentDto;
import com.adityapdev.ChaChing_api.dto.comment.CommentDetailDto;
import com.adityapdev.ChaChing_api.entity.Coin;
import com.adityapdev.ChaChing_api.entity.Comment;
import com.adityapdev.ChaChing_api.entity.User;

public class CommentMapper {

    public static CommentDetailDto mapToCommentDto(Comment comment) {
        return new CommentDetailDto(
                comment.getId(),
                comment.getTitle(),
                comment.getContent(),
                comment.getCreatedOn(),
                comment.getEditedOn(),
                comment.getUser().getUsername()
        );
    }

    public static Comment mapToComment(AddCommentDto addCommentDto, Coin coin, User user) {
        return new Comment(
                addCommentDto.getTitle(),
                addCommentDto.getContent(),
                coin,
                user
        );
    }

}
