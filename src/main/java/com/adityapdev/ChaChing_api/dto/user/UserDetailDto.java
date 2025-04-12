package com.adityapdev.ChaChing_api.dto.user;

public class UserDetailDto extends UserDto{

    public UserDetailDto(Long id, String firstName, String lastName, String username, String email, String permissionType) {
        super(id, firstName, lastName, username, email, permissionType);
    }

}
