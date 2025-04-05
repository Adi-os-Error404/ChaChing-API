package com.adityapdev.ChaChing_api.service.interfaces;

import com.adityapdev.ChaChing_api.dto.GetUserDto;
import com.adityapdev.ChaChing_api.dto.RegisterNewUserDto;

public interface IUserService {
    GetUserDto registerNewUser(RegisterNewUserDto registerNewUserDto);
}
