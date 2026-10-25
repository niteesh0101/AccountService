package com.example.accounts.Service.ServiceImp;

import com.example.accounts.Entity.Users;
import com.example.accounts.Exception.ResourceNotFounException;
import com.example.accounts.Mapper.Mapperto;
import com.example.accounts.Repository.UserRepository;
import com.example.accounts.Service.Iuser;
import com.example.accounts.dto.LoginRequest;
import com.example.accounts.dto.UserDto;
import com.example.accounts.dto.UserResponsedto;
import com.example.accounts.securites.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ImpUser implements Iuser {
    final private UserRepository userRepository;
    final private PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public ImpUser(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }
    @Override
    public ResponseEntity<UserResponsedto> registerUser(UserDto userDto) {
        Users user= Mapperto.toUser(userDto);

//        if(userRepository.findByEmail(userDto.getEmail()).isPresent()) {
//            return ResponseEntity.badRequest().body(Mapperto.toUserResponseDto(user));
//        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Users savedUser=   userRepository.save(user);

        return ResponseEntity.ok(Mapperto.toUserResponseDto(savedUser));


    }

    @Override
    public String login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return JwtService.generateToken(loginRequest.getUsername());
        }
         return "Login Failed";
    }
}
