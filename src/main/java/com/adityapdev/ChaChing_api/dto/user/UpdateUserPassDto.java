package com.adityapdev.ChaChing_api.dto.user;

public class UpdateUserPassDto {

    private final String currentPassword;
    private final String newPassword;

    public UpdateUserPassDto(String currentPassword, String newPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

}
