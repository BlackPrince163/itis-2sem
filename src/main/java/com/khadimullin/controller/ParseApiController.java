package com.khadimullin.controller;

import com.khadimullin.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;

@RestController
public class ParseApiController {
    private final Service service = new Service();

    @GetMapping("/kazan")
    public String hello(@RequestParam Optional<String> name) throws IOException {
        return Service.get(name.orElse("Kazan"));
    }
}
