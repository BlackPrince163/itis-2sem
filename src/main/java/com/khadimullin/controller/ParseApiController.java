package com.khadimullin.controller;

import com.khadimullin.service.Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;

@RestController
public class ParseApiController {
    private final Service service = new Service();

    @Operation(summary = "get weather")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "get weather",
                    content = {
                            @Content(mediaType = "application/json"
                            )
                    }
            )
    })
    @GetMapping("/kazan")
    public String hello(@RequestParam Optional<String> name) throws IOException {
        return Service.get(name.orElse("Kazan"));
    }
}
