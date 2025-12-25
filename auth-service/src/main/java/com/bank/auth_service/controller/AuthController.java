package com.bank.auth_service.controller;



import com.bank.auth_service.entity.User;
import com.bank.auth_service.AuthService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return service.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return service.login(request.getEmail(), request.getPassword());
    }
}

@Data
class LoginRequest {
    private String email;
    private String password;
}
