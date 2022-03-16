package com.khadimullin.controller;

import com.khadimullin.dto.CreateUserDto;
import com.khadimullin.dto.UserDto;
import com.khadimullin.model.User;
import com.khadimullin.repository.UserRepository;
import com.khadimullin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    @ResponseBody
    public Iterable<UserDto> getAll(@RequestParam(value = "name", required = false) Optional<String> name) {
        return name.isEmpty() ? userService.getAll() : userService.getAllByName(name.get());
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    public UserDto get(@PathVariable Integer id) {
        return userService.getById(id);
    }

    @GetMapping("/user/stepan")
    @ResponseBody
    public Iterable<UserDto> getAllStepan() {
        return userService.getAllStepan();
    }

    @PostMapping("/signUp")
    public String createUser(@Valid @ModelAttribute(name = "user") CreateUserDTO form, Model model, HttpServletRequest request){
        String url = request.getRequestURL().toString().replace(request.getServletPath(), "");
        Optional<UserDto> userDTO = userService.saveUser(form, url);
        model.addAttribute("user", userDTO);
        return "signUpSuccess";
    }

    @GetMapping("/verify")
    public String verify(@Param("code") String code) {
        if (userService.verify(code)) {
            return "verification_success";
        } else {
            return "verification_failed";
        }
    }
}