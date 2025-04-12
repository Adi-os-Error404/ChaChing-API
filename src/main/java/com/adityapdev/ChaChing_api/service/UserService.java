package com.adityapdev.ChaChing_api.service;

import com.adityapdev.ChaChing_api.config.PermissionType;
import com.adityapdev.ChaChing_api.dto.user.RegisterNewUserDto;
import com.adityapdev.ChaChing_api.dto.user.UpdateUserPassDto;
import com.adityapdev.ChaChing_api.dto.user.UserDetailDto;
import com.adityapdev.ChaChing_api.entity.Permission;
import com.adityapdev.ChaChing_api.entity.User;
import com.adityapdev.ChaChing_api.exception.ConflictException;
import com.adityapdev.ChaChing_api.exception.ResourceNotFoundException;
import com.adityapdev.ChaChing_api.exception.UnauthorizedException;
import com.adityapdev.ChaChing_api.mapper.UserMapper;
import com.adityapdev.ChaChing_api.repository.PermissionRepository;
import com.adityapdev.ChaChing_api.repository.UserRepository;
import com.adityapdev.ChaChing_api.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final PermissionRepository permissionRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTService jwtService;

    public UserService(UserRepository userRepository, PermissionRepository permissionRepository) {
        this.userRepository = userRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public UserDetailDto registerNewUser(RegisterNewUserDto registerNewUserDto) {
        Optional<User> existingUserEmail = userRepository.findByEmail(registerNewUserDto.getEmail());
        Optional<User> existingUserName = userRepository.findByUsername(registerNewUserDto.getUsername());

        if (existingUserEmail.isPresent())
            throw new ConflictException(String.format("Email \"%s\" is already registered.", registerNewUserDto.getEmail()));
        if (existingUserName.isPresent())
            throw new ConflictException(String.format("Username \"%s\" is already registered.", registerNewUserDto.getUsername()));

        Permission permission = validatePermissionType(registerNewUserDto.getPermissionType());
        registerNewUserDto.setPassword(encoder.encode(registerNewUserDto.getPassword()));
        User user = UserMapper.mapToUser(registerNewUserDto, permission);
        User savedUser = userRepository.save(user);
        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public String verifyUserCredentials(String username, String password) {
        String failRes = "Username or Password is incorrect";
        try {
            Authentication auth = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
            if (auth.isAuthenticated()) {
                return jwtService.generateToken(username);
            }
        } catch (Exception e) {
            throw new UnauthorizedException(failRes);
        }
        return failRes;
    }

    @Override
    public List<UserDetailDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
    }

    @Override
    public UserDetailDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User with email \"%s\" does not exist.", email)));
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public UserDetailDto updateUserName(Long id, UserDetailDto userDetailDto) {
        User user = getUserById(id);
        user.setFirstName(userDetailDto.getFirstName());
        user.setLastName(userDetailDto.getLastName());
        User updateUser = userRepository.save(user);
        return UserMapper.mapToUserDto(updateUser);
    }

    @Override
    public UserDetailDto updateUserPermission(Long id, UserDetailDto userDetailDto) {
        User user = getUserById(id);
        Permission permission = validatePermissionType(userDetailDto.getPermissionType());
        user.setPermission(permission);
        User updateUser = userRepository.save(user);
        return UserMapper.mapToUserDto(updateUser);
    }

    @Override
    public UserDetailDto updateUserPassword(Long id, UpdateUserPassDto updateUserPassDto) {
        User user = getUserById(id);
        validatePassword(user.getPassword(), updateUserPassDto.getCurrentPassword());
        user.setPassword(updateUserPassDto.getNewPassword());
        User updateUser = userRepository.save(user);
        return UserMapper.mapToUserDto(updateUser);
    }

    @Override
    public void deleteUser(long id) {
        User user = getUserById(id);
        userRepository.deleteById(id);
    }

    // Helpers:
    private User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User with id \"%d\" does not exist", id)));
    }
    private void validatePassword(String enteredPassword, String actualPassword) {
        if (!Objects.equals(enteredPassword, actualPassword))
            throw new UnauthorizedException("Current password is incorrect.");
    }

    private Permission validatePermissionType(String permissionTypeStr) {
        PermissionType permissionType;
        try {
            permissionType = PermissionType.valueOf(permissionTypeStr);
        } catch (IllegalArgumentException e) {
            throw new ConflictException("Invalid permission type: " + permissionTypeStr);
        }
        return permissionRepository.findByPermissionType(permissionType)
                .orElseThrow(() -> new ConflictException("Invalid permission type"));
    }

}
