package com.khadimullin.service;

import com.khadimullin.dto.CreateUserDto;
import com.khadimullin.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto getByEmail(String email);

    UserDto getById(Integer id);

    List<UserDto> getAll();

    UserDto signUp(CreateUserDto createUserDto, String url);

    UserDto save(CreateUserDto createUserDto);

    List<UserDto> getAllStepan();

    List<UserDto> getAllByName(String name);

    boolean verify(String verificationCode);

    void sendVerificationMail(String mail, String name, String code, String url);
}
