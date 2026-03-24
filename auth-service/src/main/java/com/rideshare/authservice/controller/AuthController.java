package com.rideshare.authservice.controller;

import com.rideshare.authservice.dto.AuthResponse;
import com.rideshare.authservice.dto.LoginRequest;
import com.rideshare.authservice.dto.RegisterRequest;
import com.rideshare.authservice.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Rider/Driver registration and login")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "Register user", responses = {
        @ApiResponse(responseCode = "201", description = "Registered successfully"),
        @ApiResponse(responseCode = "400", description = "Validation or duplicate email")
    })
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse register(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(examples = @ExampleObject(value = "{" +
            "\"fullName\":\"John Rider\"," +
            "\"email\":\"john@example.com\"," +
            "\"password\":\"Pass@123\"," +
            "\"role\":\"RIDER\"}")))
        @Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @Operation(summary = "Login user", responses = {
        @ApiResponse(responseCode = "200", description = "Token issued"),
        @ApiResponse(responseCode = "400", description = "Invalid credentials")
    })
    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
