package com.adityapdev.ChaChing_api.dto;

import com.adityapdev.ChaChing_api.config.PermissionType;
import com.adityapdev.ChaChing_api.entity.Permission;

public abstract class UserDto {

    private final Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String permissionType;

    public UserDto(Long id, String firstName, String lastName, String email, String permissionType) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.permissionType = permissionType;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(PermissionType permission) {
        this.permissionType = permissionType;
    }

}
