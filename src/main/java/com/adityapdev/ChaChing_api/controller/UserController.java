package com.adityapdev.ChaChing_api.controller;


import com.adityapdev.ChaChing_api.dto.RegisterNewUserDto;
import com.adityapdev.ChaChing_api.dto.GetUserDto;
import com.adityapdev.ChaChing_api.service.interfaces.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<GetUserDto> registerUser(@RequestBody RegisterNewUserDto registerNewUserDto) {
        GetUserDto savedUser = userService.registerNewUser(registerNewUserDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

}
