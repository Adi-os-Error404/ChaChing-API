package com.adityapdev.ChaChing_api.dto;

import com.adityapdev.ChaChing_api.util.Security;

public class UpdateUserDto extends UserDto{

    private final String currentPassword;
    private final String newPassword;

    public UpdateUserDto(Long id, String firstName, String lastName, String email, String currentPassword, String newPassword) {
        super(id, firstName, lastName, email);
        this.currentPassword = Security.hashPassword(currentPassword);
        this.newPassword = Security.hashPassword(newPassword);
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

}
