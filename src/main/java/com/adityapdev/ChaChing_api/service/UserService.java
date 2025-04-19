package com.adityapdev.ChaChing_api.service;

import com.adityapdev.ChaChing_api.dto.user.*;
import com.adityapdev.ChaChing_api.entity.User;
import com.adityapdev.ChaChing_api.exception.ConflictException;
import com.adityapdev.ChaChing_api.exception.ResourceNotFoundException;
import com.adityapdev.ChaChing_api.exception.UnauthorizedException;
import com.adityapdev.ChaChing_api.mapper.UserMapper;
import com.adityapdev.ChaChing_api.repository.UserRepository;
import com.adityapdev.ChaChing_api.service.interfaces.IJWTService;
import com.adityapdev.ChaChing_api.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private IJWTService jwtService;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetailDto registerNewUser(RegisterNewUserDto registerNewUserDto) {
        String userEmail = registerNewUserDto.getEmail();
        String userName = registerNewUserDto.getUsername();
        Optional<User> existingUserEmail = userRepository.findByEmail(userEmail);
        Optional<User> existingUserName = userRepository.findByUsername(userName);

        if (existingUserEmail.isPresent())
            throw new ConflictException(String.format("Email \"%s\" is already registered", userEmail));
        if (existingUserName.isPresent())
            throw new ConflictException(String.format("Username \"%s\" is already registered", userName));

        registerNewUserDto.setPassword(hashPassword(registerNewUserDto.getPassword()));
        User user = UserMapper.mapToUser(registerNewUserDto);
        User savedUser = userRepository.save(user);
        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public LoginRespUserDto verifyUserCredentials(String username, String password) {
        authenticateUser(username, password);
        String token = jwtService.generateToken(username);
        long expiry = jwtService.getTokenExpiry(token);
        return new LoginRespUserDto(username, token, expiry);
    }


    @Override
    public UserDetailDto getLoggedInUser() {
        User user = getCurrentUser();
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public UserDetailDto updateUserFirstLastName(UpdateUserNamesDto updateUserNamesDto) {
        User user = getCurrentUser();
        user.setFirstName(updateUserNamesDto.getFirstName());
        user.setLastName(updateUserNamesDto.getLastName());
        User updateUser = userRepository.save(user);
        return UserMapper.mapToUserDto(updateUser);
    }

    @Override
    public UserDetailDto updateUserPassword(UpdateUserPassDto updateUserPassDto) {
        User user = getCurrentUser();
        authenticateUser(user.getUsername(), updateUserPassDto.getCurrentPassword());
        user.setPassword(hashPassword(updateUserPassDto.getNewPassword()));
        User updateUser = userRepository.save(user);
        return UserMapper.mapToUserDto(updateUser);
    }

    @Override
    public void deleteUser(LoginUserDto loginUserDto) {
        authenticateUser(loginUserDto.getUsername(), loginUserDto.getPassword());
        userRepository.deleteById(getCurrentUser().getId());
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        return userRepository.findByUsername(userName)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User \"%s\" is not found", userName)));
    }

    private void authenticateUser(String username, String password) {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }
        catch (Exception e) {
            throw new UnauthorizedException("Username or Password is incorrect");
        }
    }

    private String hashPassword(String enteredPassword) {
        return encoder.encode(enteredPassword);
    }

}
