package com.adityapdev.ChaChing_api.controller;


import com.adityapdev.ChaChing_api.dto.RegisterNewUserDto;
import com.adityapdev.ChaChing_api.dto.UpdateUserDto;
import com.adityapdev.ChaChing_api.dto.UserDetailDto;
import com.adityapdev.ChaChing_api.service.interfaces.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDetailDto> registerUser(@RequestBody RegisterNewUserDto registerNewUserDto) {
        UserDetailDto savedUser = userService.registerNewUser(registerNewUserDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserDetailDto>> getAllUsers() {
        List<UserDetailDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("{email}")
    public ResponseEntity<UserDetailDto> getUserByEmail(@PathVariable("email") String email) {
        UserDetailDto userDto = userService.getUserByEmail(email);
        return ResponseEntity.ok(userDto);
    }

    @PutMapping
    public ResponseEntity<UserDetailDto> updateUserDetail(@RequestBody UpdateUserDto updatedUserDto) {
        UserDetailDto userDto = userService.updateUser(updatedUserDto);
        return ResponseEntity.ok(userDto);
    }

}
