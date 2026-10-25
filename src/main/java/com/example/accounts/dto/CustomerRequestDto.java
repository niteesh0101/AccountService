package com.example.accounts.dto;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.NonNull;

@Data
public class CustomerRequestDto {
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @Column(unique = true)
    private String email;
    private String phoneNumber;
    private String address;
    private String city;
    private String state;
    private String country;
    private String zipCode;
}
