package com.adityapdev.ChaChing_api.dto.user;

public class LoginRespUserDto {
    private String username;
    private String token;
    private long tokenExpiry;

    public LoginRespUserDto(String username, String token, long tokenExpiry) {
        this.username = username;
        this.token = token;
        this.tokenExpiry = tokenExpiry;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }

    public long getTokenExpiry() {
        return tokenExpiry;
    }
}
