package com.khadimullin.dto;

import com.khadimullin.model.User;

public class UserDto {
    private Integer id;

    private String name;

    private String email;

   private String hashPassword;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public UserDto(Integer id, String name, String email, String hashPassword) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.hashPassword = hashPassword;
    }

    public static UserDto fromModel(User user) {
        return new UserDto(user.getId(), user.getName(), user.getEmail(), user.getHashPassword());
    }
}
