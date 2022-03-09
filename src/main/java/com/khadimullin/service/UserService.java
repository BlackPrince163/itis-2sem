package com.khadimullin.service;

import com.khadimullin.dto.CreateUserDto;
import com.khadimullin.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto getByEmail(String email);

    UserDto getById(Integer id);

    List<UserDto> getAll();

    UserDto save(CreateUserDto createUserDto);

    List<UserDto> getAllStepan();

    List<UserDto> getAllByName(String name);
}
