package com.adityapdev.ChaChing_api.dto.user;

import com.adityapdev.ChaChing_api.config.PermissionType;
import com.adityapdev.ChaChing_api.util.Security;

public class RegisterNewUserDto extends UserDto{

    private final String password;

    public RegisterNewUserDto(Long id, String firstName, String lastName, String email, String password) {
        super(id, firstName, lastName, email, PermissionType.BASE_USER.toString());
        this.password = Security.hashPassword(password);
    }

    public String getPassword() {
        return password;
    }

}
