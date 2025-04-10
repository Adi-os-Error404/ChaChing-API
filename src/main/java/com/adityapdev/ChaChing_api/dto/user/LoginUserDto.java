package com.adityapdev.ChaChing_api.dto.user;

import com.adityapdev.ChaChing_api.util.Security;

public class LoginUserDto {

    private String email;
    private String password;

    public LoginUserDto(String email, String password) {
        this.email = email;
        this.password = Security.hashPassword(password);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
