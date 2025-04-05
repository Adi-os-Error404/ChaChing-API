package com.adityapdev.ChaChing_api.service;

import com.adityapdev.ChaChing_api.dto.RegisterNewUserDto;
import com.adityapdev.ChaChing_api.dto.GetUserDto;
import com.adityapdev.ChaChing_api.entity.User;
import com.adityapdev.ChaChing_api.mapper.UserMapper;
import com.adityapdev.ChaChing_api.repository.UserRepository;
import com.adityapdev.ChaChing_api.service.interfaces.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public GetUserDto registerNewUser(RegisterNewUserDto registerNewUserDto) {
        User user = UserMapper.mapToUser(registerNewUserDto);
        User savedUser = userRepository.save(user);
        return UserMapper.mapToUserDto(savedUser);
    }
}
