package com.khadimullin.service.impl;

import com.khadimullin.dto.CreateUserDto;
import com.khadimullin.dto.UserDto;
import com.khadimullin.model.User;
import com.khadimullin.repository.UserRepository;
import com.khadimullin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public UserDto getByEmail(String email) {
        return userRepository.getUserByEmail(email).stream().map(UserDto::fromModel).findFirst().orElse(null);
    }

    @Override
    public UserDto getById(Integer id) {
        return userRepository.findById(id).stream().map(UserDto::fromModel).findFirst().orElse(null);
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(UserDto::fromModel).collect(Collectors.toList());
    }

    @Override
    public UserDto save(CreateUserDto createUserDto) {
        User user = new User(createUserDto.getName(), createUserDto.getEmail());
        user.setPassword(encoder.encode(createUserDto.getPassword()));
        return UserDto.fromModel(userRepository.save(user));
    }

    @Override
    public List<UserDto> getAllStepan() {
        return userRepository.findAllStepanUser().stream().map(UserDto::fromModel).collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getAllByName(String name) {
        return userRepository.findAllByName(name).stream().map(UserDto::fromModel).collect(Collectors.toList());
    }
}
