package com.adityapdev.ChaChing_api.dto.user;

public class LoginUserDto {

    private String username;
    private String password;

    public LoginUserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
