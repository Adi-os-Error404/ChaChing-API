package com.adityapdev.ChaChing_api.dto;

import com.adityapdev.ChaChing_api.util.Security;

public class RegisterNewUserDto extends UserDto{

    private final String password;

    public RegisterNewUserDto(Long id, String firstName, String lastName, String email, String password) {
        super(id, firstName, lastName, email);
        this.password = Security.hashPassword(password);
    }

    public String getPassword() {
        return password;
    }

}
