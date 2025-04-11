package com.adityapdev.ChaChing_api.dto.comment;

import java.time.Instant;

public abstract class CommentDto {
    private Long id;
    private String title;
    private String content;

    protected CommentDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
    protected CommentDto(String title, String content) {
        this.title = title;
        this.content = content;
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

    public String getContent() {
        return content;
    }

}
