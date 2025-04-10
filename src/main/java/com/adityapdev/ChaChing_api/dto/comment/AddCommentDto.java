package com.adityapdev.ChaChing_api.dto.comment;

import java.time.Instant;

public class AddCommentDto extends CommentDto{

    protected AddCommentDto(Long id, String title, String content, Instant createdOn, Instant editedOn, String coinId) {
        super(id, title, content, createdOn, editedOn, coinId);
    }

}
