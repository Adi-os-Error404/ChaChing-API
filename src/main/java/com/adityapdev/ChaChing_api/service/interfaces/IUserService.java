package com.adityapdev.ChaChing_api.service.interfaces;

import com.adityapdev.ChaChing_api.dto.user.UpdateUserPassDto;
import com.adityapdev.ChaChing_api.dto.user.UserDetailDto;
import com.adityapdev.ChaChing_api.dto.user.RegisterNewUserDto;

import java.util.List;

public interface IUserService {
    UserDetailDto registerNewUser(RegisterNewUserDto registerNewUserDto);
    UserDetailDto verifyUserCredentials(String email, String password);
    List<UserDetailDto> getAllUsers();
    UserDetailDto getUserByEmail(String email);
    UserDetailDto updateUserName(Long id, UserDetailDto userDetailDto);
    UserDetailDto updateUserPermission(Long id, UserDetailDto userDetailDto);
    UserDetailDto updateUserPassword(Long id, UpdateUserPassDto updateUserPassDto);
    void deleteUser(long id);

}
