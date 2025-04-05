package com.adityapdev.ChaChing_api.service;

import com.adityapdev.ChaChing_api.dto.RegisterNewUserDto;
import com.adityapdev.ChaChing_api.dto.UpdateUserDto;
import com.adityapdev.ChaChing_api.dto.UserDetailDto;
import com.adityapdev.ChaChing_api.entity.User;
import com.adityapdev.ChaChing_api.exception.ConflictException;
import com.adityapdev.ChaChing_api.exception.ResourceNotFoundException;
import com.adityapdev.ChaChing_api.exception.UnauthorizedException;
import com.adityapdev.ChaChing_api.mapper.UserMapper;
import com.adityapdev.ChaChing_api.repository.UserRepository;
import com.adityapdev.ChaChing_api.service.interfaces.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetailDto registerNewUser(RegisterNewUserDto registerNewUserDto) {
        Optional<User> existingUser = userRepository.findByEmail(registerNewUserDto.getEmail());
        if (existingUser.isPresent())
            throw new ConflictException(String.format("Email \"%s\" is already registered.", registerNewUserDto.getEmail()));

        User user = UserMapper.mapToUser(registerNewUserDto);
        User savedUser = userRepository.save(user);
        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public UserDetailDto verifyUserCredentials(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ConflictException(String.format("Email \"%s\" is not registered.", email)));

        if (!Objects.equals(user.getPassword(), password))
            throw new UnauthorizedException("Password entered is incorrect.");
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public List<UserDetailDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
    }

    @Override
    public UserDetailDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User with email %s does not exist.", email)));
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public UserDetailDto updateUser(UpdateUserDto updateUserDto) {
        Optional<User> optionalUser = userRepository.findById(updateUserDto.getId());
        if (optionalUser.isEmpty())
            throw new ResourceNotFoundException("User does not exist");

        User user = optionalUser.get();

        if (!Objects.equals(user.getPassword(), updateUserDto.getCurrentPassword()))
            throw new UnauthorizedException("Current password is incorrect.");

        if (!Objects.equals(user.getEmail(), updateUserDto.getEmail()) && userRepository.findByEmail(updateUserDto.getEmail()).isPresent())
            throw new ConflictException(String.format("Email %s is already registered.", updateUserDto.getEmail()));

        user.setFirstName(updateUserDto.getFirstName());
        user.setLastName(updateUserDto.getLastName());
        user.setEmail(updateUserDto.getEmail());
        user.setPassword(updateUserDto.getNewPassword());

        User updateUser = userRepository.save(user);
        return UserMapper.mapToUserDto(updateUser);
    }

    @Override
    public void deleteUser(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User with id %d does not exist.", id)));
        userRepository.deleteById(id);
    }

}
