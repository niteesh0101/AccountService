package com.example.accounts.controller;

import com.example.accounts.Service.Iuser;
import com.example.accounts.dto.LoginRequest;
import com.example.accounts.dto.UserDto;
import com.example.accounts.dto.UserResponsedto;
import com.example.accounts.securites.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final AuthenticationManager authenticationManager;
    private Iuser service;
    UserController(Iuser service, AuthenticationManager authenticationManager){
        this.service = service;
        this.authenticationManager = authenticationManager;
    }
    @PostMapping("/register")
    public ResponseEntity<UserResponsedto> registerUser(@RequestBody UserDto userDto) {
        return service.registerUser(userDto);

    }
    @PostMapping("/login")
    public String loginUser(@RequestBody LoginRequest loginRequest) {
//        return service.login(loginRequest);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return JwtService.generateToken(loginRequest.getUsername());
        }
        return "Login Failed";
    }
}
