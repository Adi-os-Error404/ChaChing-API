package com.adityapdev.ChaChing_api.dto.comment;

import java.time.Instant;

public class CommentDetailDto extends CommentDto{

    private Instant createdOn;
    private Instant editedOn;
    private String username;

    public CommentDetailDto(Long id, String title, String content, Instant createdOn, Instant editedOn, String username) {
        super(id, title, content);
        this.createdOn = createdOn;
        this.editedOn = editedOn;
        this.username = username;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public Instant getEditedOn() {
        return editedOn;
    }

    public String getUsername() {
        return username;
    }

}
