package com.adityapdev.ChaChing_api.dto;

import com.adityapdev.ChaChing_api.config.PermissionType;
import com.adityapdev.ChaChing_api.util.Security;

public class RegisterNewUserDto extends UserDto{

    private final String password;

    public RegisterNewUserDto(Long id, String firstName, String lastName, String email, String password, String permissionType) {
        super(id, firstName, lastName, email, permissionType);
        this.password = Security.hashPassword(password);
    }

    public String getPassword() {
        return password;
    }

}
