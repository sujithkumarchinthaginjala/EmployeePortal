package com.example.PayslipGenerator.Service;

import com.example.PayslipGenerator.DTO.AuthRequest;
import com.example.PayslipGenerator.DTO.AuthResponse;
import com.example.PayslipGenerator.DTO.RegisterRequest;
import com.example.PayslipGenerator.Model.User;
import com.example.PayslipGenerator.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;

    public AuthService(PasswordEncoder passwordEncoder, AuthenticationManager authManager) {
        this.passwordEncoder = passwordEncoder;
        this.authManager = authManager;
    }

    @Autowired
    private JwtService jwtService;

    public AuthResponse login(AuthRequest request) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(null);
        String token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }

//    public AuthResponse register(RegisterRequest request) {
//        User user = User.builder()
//                .name(request.getName())
//                .email(request.getEmail())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .roles(request.getRoles())
//                .build();
//        userRepository.save(user);
//        String token = jwtService.generateToken(user.getEmail(), user.getRoles().stream().toList());
//        return new AuthResponse(token);
//    }
}
