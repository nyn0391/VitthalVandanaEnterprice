package com.vitthalvandana.user.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.vitthalvandana.user.service.AuthService;
import com.vitthalvandana.user.model.RegisterRequest;
import com.vitthalvandana.user.model.LoginRequest;
import com.vitthalvandana.user.model.ValidationResponse;
import com.vitthalvandana.user.model.User;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthResource {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @GetMapping("/validate")
    public ValidationResponse validate(@RequestHeader("Authorization") String token) {
        return authService.validateToken(token);
    }
}