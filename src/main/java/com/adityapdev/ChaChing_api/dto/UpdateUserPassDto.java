package com.adityapdev.ChaChing_api.dto;

import com.adityapdev.ChaChing_api.util.Security;

public class UpdateUserPassDto extends UserDto{

    private final String currentPassword;
    private final String newPassword;

    public UpdateUserPassDto(Long id, String firstName, String lastName, String email, String currentPassword, String newPassword, String permissionType) {
        super(id, firstName, lastName, email, permissionType);
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
