package com.adityapdev.ChaChing_api.dto.comment;

import java.time.Instant;

public class CommentDetailDto extends CommentDto{
    public CommentDetailDto(Long id, String title, String content, Instant createdOn, Instant editedOn, String coinId) {
        super(id, title, content, createdOn, editedOn, coinId);
    }
}
