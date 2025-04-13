package com.adityapdev.ChaChing_api.service.interfaces;

import com.adityapdev.ChaChing_api.dto.user.*;

import java.util.List;

public interface IUserService {
    UserDetailDto registerNewUser(RegisterNewUserDto registerNewUserDto);
    String verifyUserCredentials(String username, String password);
    UserDetailDto getLoggedInUser();
    UserDetailDto updateUserFirstLastName(UpdateUserNamesDto updateUserNamesDto);
    UserDetailDto updateUserPassword(UpdateUserPassDto updateUserPassDto);
    void deleteUser(LoginUserDto loginUserDto);

}
