package com.example.PayslipGenerator.Controller;

import com.example.PayslipGenerator.DTO.AuthRequest;
import com.example.PayslipGenerator.DTO.AuthResponse;
import com.example.PayslipGenerator.DTO.RegisterRequest;
import com.example.PayslipGenerator.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return authService.login(request);
    }

//    @PostMapping("/register")
//    public AuthResponse register(@RequestBody RegisterRequest request) {
//        return authService.register(request);
//    }
}
