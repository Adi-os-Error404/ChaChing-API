package com.adityapdev.ChaChing_api.mapper;

import com.adityapdev.ChaChing_api.dto.user.RegisterNewUserDto;
import com.adityapdev.ChaChing_api.dto.user.UserDetailDto;
import com.adityapdev.ChaChing_api.entity.Permission;
import com.adityapdev.ChaChing_api.entity.User;

// remove this class - we do not need this for users
public class UserMapper {
    public static UserDetailDto mapToUserDto(User user) {
        return new UserDetailDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPermission().getPermissionType().toString()
        );
    }
    public static User mapToUser(RegisterNewUserDto registerNewUserDto, Permission permission) {
        return new User(
                registerNewUserDto.getId(),
                registerNewUserDto.getFirstName(),
                registerNewUserDto.getLastName(),
                registerNewUserDto.getEmail(),
                registerNewUserDto.getPassword(),
                permission
        );
    }
}
