package com.adityapdev.ChaChing_api.dto.comment;

import java.time.Instant;

public class CommentDetailDto extends CommentDto{
    private Instant createdOn;
    private Instant editedOn;

    public CommentDetailDto(Long id, String title, String content, Instant createdOn, Instant editedOn) {
        super(id, title, content);
        this.createdOn = createdOn;
        this.editedOn = editedOn;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public Instant getEditedOn() {
        return editedOn;
    }

}
