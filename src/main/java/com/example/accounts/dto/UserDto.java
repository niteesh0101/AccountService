package com.example.accounts.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class UserDto {
   @NonNull
    private String firstName;
    private String lastName;
    @NonNull
    private String email;
    @NonNull
    private String password;
    @NonNull
    private String mobileNo;
 private String role;
}
