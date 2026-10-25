package com.example.accounts.Service;

import com.example.accounts.dto.LoginRequest;
import com.example.accounts.dto.UserDto;
import com.example.accounts.dto.UserResponsedto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface Iuser {
    public ResponseEntity<UserResponsedto> registerUser(UserDto userDto);

    public String login(LoginRequest loginRequest);
}
