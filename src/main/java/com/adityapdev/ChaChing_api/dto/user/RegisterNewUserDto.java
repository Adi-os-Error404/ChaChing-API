package com.adityapdev.ChaChing_api.dto.user;

public class RegisterNewUserDto extends UserDto{

    private String password;

    public RegisterNewUserDto(Long id, String firstName, String lastName, String username, String email, String password) {
        super(id, firstName, lastName, username, email);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
