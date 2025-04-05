package com.adityapdev.ChaChing_api.mapper;

import com.adityapdev.ChaChing_api.dto.RegisterNewUserDto;
import com.adityapdev.ChaChing_api.dto.GetUserDto;
import com.adityapdev.ChaChing_api.entity.User;
import com.adityapdev.ChaChing_api.util.Security;

// remove this class - we do not need this for users
public class UserMapper {
    public static GetUserDto mapToUserDto(User user) {
        return new GetUserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
    }
    public static User mapToUser(RegisterNewUserDto registerNewUserDto) {
        return new User(
                registerNewUserDto.getId(),
                registerNewUserDto.getFirstName(),
                registerNewUserDto.getLastName(),
                registerNewUserDto.getEmail(),
                registerNewUserDto.getPassword()
        );
    }
}
