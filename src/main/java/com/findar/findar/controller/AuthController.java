package com.findar.findar.controller;


import com.findar.findar.config.JWTTokenProvider;
import com.findar.findar.models.*;
import com.findar.findar.repository.UserRepository;
import com.findar.findar.service.UserAuthDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/authenticate")
@Slf4j
public class AuthController {





    @Autowired
    private JWTTokenProvider jwtTokenProvider;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserAuthDetailsService userAuthDetailsService;

    @PostMapping
    public ResponseEntity authenticateUser(@RequestBody AuthenticateRequest authenticateRequest) {
        UserPrincipal user = userAuthDetailsService.loadUserByUsername(authenticateRequest.getUserName());
        log.info("User Data {}",authenticateRequest.getUserName());
        if (user != null) {
           ;
            boolean passwordMatches = passwordEncoder.matches(authenticateRequest.getPassword(), user.getPassword());
            if (passwordMatches) {
                String token =jwtTokenProvider.generateToken(user);
                log.info("Token Created {}",token);
                return ResponseEntity.ok(new JwtAuthenticationResponse(token));
            } else {
                System.out.println("Password does not match!");
                return new ResponseEntity(new CustomResponse(false, "Password does not match"), HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity(new CustomResponse(false, "User not found"), HttpStatus.NOT_FOUND);
        }


    }
}
