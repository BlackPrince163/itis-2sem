package com.khadimullin.dto;

import javax.validation.constraints.NotBlank;

public class CreateUserDto {

    @NotBlank(message = "Name shouldn't be blank!")
    private String name;

    @NotBlank(message = "Email shouldn't be blank!")
    private String email;

    @NotBlank(message = "Password shouldn't be blank!")
    private String hashPassword;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String password) {
        this.hashPassword = password;
    }

    public CreateUserDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.hashPassword = password;
    }
}
