package com.adityapdev.ChaChing_api.dto.user;

public class LoginUserDto {

    private String username;
    private String password;
    private String token;

    public LoginUserDto(String username, String password, String token) {
        this.username = username;
        this.password = password;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }


}
