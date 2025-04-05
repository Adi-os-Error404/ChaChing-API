package com.adityapdev.ChaChing_api.dto;

public class GetUserDto extends UserDto{

    public GetUserDto(Long id, String firstName, String lastName, String email) {
        super(id, firstName, lastName, email);
    }

}
