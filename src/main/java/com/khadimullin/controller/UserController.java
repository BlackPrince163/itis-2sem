package com.khadimullin.controller;

import com.khadimullin.dto.CreateUserDto;
import com.khadimullin.dto.UserDto;
import com.khadimullin.model.User;
import com.khadimullin.repository.UserRepository;
import com.khadimullin.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    public UserDto getUser(@PathVariable Integer id) {
        return userService.getById(id);
    }

//    @PostMapping("/user")
//    @ResponseBody
//    public UserDto createUser(@Valid @RequestBody CreateUserDto user) {
//        return userService.signUp(user);
//    }

    @GetMapping("/user/stepan")
    @ResponseBody
    public Iterable<UserDto> getAllStepan() {
        return userService.getAllStepan();
    }

    @Operation(summary = "Create user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User was created",
                    content = {
                            @Content(mediaType = "text/html"
                            )
                    }
            )
    })
    @PostMapping("/sign_up")
    public String signUp(@ModelAttribute(name = "user") CreateUserDto userDto, HttpServletRequest request) {
        String url = request.getRequestURL().toString().replace(request.getServletPath(), "");
        userService.signUp(userDto, url);
        return "sign_up_success";
    }

    @Operation(summary = "Verify user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User was verified",
                    content = {
                            @Content(mediaType = "text/html"
                            )
                    }
            )
    })
    @GetMapping("/verify")
    public String verify(@Param("code") String code) {
        if (userService.verify(code)) {
            return "verification_success";
        } else {
            return "verification_failed";
        }
    }
}