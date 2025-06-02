package com.example.PayslipGenerator.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.PayslipGenerator.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class JwtService {

    private final String SECRET = "secret";
    private final long EXPIRATION = 3600_000;

    public String generateToken(User user) {
        System.out.println("Roles for token: " + user.getRoles());
        return JWT.create()
                .withSubject(user.getEmail())
                .withClaim("roles", new ArrayList<>(user.getRoles()))
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION))
                .sign(Algorithm.HMAC256(SECRET));
    }
}
