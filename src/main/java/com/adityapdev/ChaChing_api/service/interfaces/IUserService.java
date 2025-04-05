package com.adityapdev.ChaChing_api.service.interfaces;

import com.adityapdev.ChaChing_api.dto.UpdateUserDto;
import com.adityapdev.ChaChing_api.dto.UserDetailDto;
import com.adityapdev.ChaChing_api.dto.RegisterNewUserDto;

import java.util.List;

public interface IUserService {
    UserDetailDto registerNewUser(RegisterNewUserDto registerNewUserDto);
    List<UserDetailDto> getAllUsers();
    UserDetailDto getUserByEmail(String email);
    UserDetailDto updateUser(UpdateUserDto updateUserDto);
    void deleteUser(long id);

}
