package com.adityapdev.ChaChing_api.dto;

import com.adityapdev.ChaChing_api.util.Security;

public class UpdateUserDto extends UserDto{

    private final String currentPassword;
    private final String newPassword;
    private final String newEmail;

    public UpdateUserDto(Long id, String firstName, String lastName, String email, String currentPassword, String newPassword, String newEmail) {
        super(id, firstName, lastName, email);
        this.currentPassword = Security.hashPassword(currentPassword);
        this.newPassword = Security.hashPassword(newPassword);
        this.newEmail = newEmail;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getNewEmail() {
        return newEmail;
    }

}
