package com.adityapdev.ChaChing_api.mapper;

import com.adityapdev.ChaChing_api.dto.user.RegisterNewUserDto;
import com.adityapdev.ChaChing_api.dto.user.UserDetailDto;
import com.adityapdev.ChaChing_api.entity.User;

public class UserMapper {
    public static UserDetailDto mapToUserDto(User user) {
        return new UserDetailDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getEmail()
        );
    }
    public static User mapToUser(RegisterNewUserDto registerNewUserDto) {
        return new User(
                registerNewUserDto.getId(),
                registerNewUserDto.getFirstName(),
                registerNewUserDto.getLastName(),
                registerNewUserDto.getUsername(),
                registerNewUserDto.getEmail(),
                registerNewUserDto.getPassword()
        );
    }
}
