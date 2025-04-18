package com.adityapdev.ChaChing_api.controller;


import com.adityapdev.ChaChing_api.dto.user.*;
import com.adityapdev.ChaChing_api.exception.UnauthorizedException;
import com.adityapdev.ChaChing_api.service.interfaces.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/account")
public class UserController {

    private IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDetailDto> registerUser(@RequestBody RegisterNewUserDto registerNewUserDto) {
        UserDetailDto savedUser = userService.registerNewUser(registerNewUserDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginUserDto> loginUser(@RequestBody LoginUserDto loginUserDto) {
        LoginUserDto res = userService.verifyUserCredentials(loginUserDto.getUsername(), loginUserDto.getPassword());
        return ResponseEntity.ok(res);
    }

    @GetMapping
    public ResponseEntity<UserDetailDto> getUserDetails() {
        UserDetailDto userDto = userService.getLoggedInUser();
        return ResponseEntity.ok(userDto);
    }

    @PutMapping("/name")
    public ResponseEntity<UserDetailDto> updateUserFirstLastName(@RequestBody UpdateUserNamesDto updateUserNamesDto) {
        UserDetailDto userDto = userService.updateUserFirstLastName(updateUserNamesDto);
        return ResponseEntity.ok(userDto);
    }


    @PutMapping("/password")
    public ResponseEntity<UserDetailDto> updateUserPassword(@RequestBody UpdateUserPassDto updatedUserDto) {
        UserDetailDto userDto = userService.updateUserPassword(updatedUserDto);
        return ResponseEntity.ok(userDto);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUser(@RequestBody LoginUserDto loginUserDto) {
        userService.deleteUser(loginUserDto);
        return ResponseEntity.ok("User deleted successfully.");
    }

}
