package com.adityapdev.ChaChing_api.dto.comment;

import java.time.Instant;

public abstract class CommentDto {
    private Long id;
    private String title;
    private String content;
    private Instant createdOn;
    private Instant editedOn;
    private String coinId;

    protected CommentDto(Long id, String title, String content, Instant createdOn, Instant editedOn, String coinId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdOn = createdOn;
        this.editedOn = editedOn;
        this.coinId = coinId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }

    public Instant getEditedOn() {
        return editedOn;
    }

    public void setEditedOn(Instant editedOn) {
        this.editedOn = editedOn;
    }

    public String getCoinId() {
        return coinId;
    }

    public void setCoinId(String coinId) {
        this.coinId = coinId;
    }
}
