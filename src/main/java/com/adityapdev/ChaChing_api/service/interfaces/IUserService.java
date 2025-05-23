package com.adityapdev.ChaChing_api.service.interfaces;

import com.adityapdev.ChaChing_api.dto.user.*;
import com.adityapdev.ChaChing_api.entity.User;

import java.util.List;

public interface IUserService {
    UserDetailDto registerNewUser(RegisterNewUserDto registerNewUserDto);
    LoginRespUserDto verifyUserCredentials(String username, String password);
    UserDetailDto getLoggedInUser();
    UserDetailDto updateUserFirstLastName(UpdateUserNamesDto updateUserNamesDto);
    UserDetailDto updateUserPassword(UpdateUserPassDto updateUserPassDto);
    void deleteUser(LoginUserDto loginUserDto);
    User getCurrentUser();
}
