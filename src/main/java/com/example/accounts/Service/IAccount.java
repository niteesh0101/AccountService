package com.example.accounts.Service;

import com.example.accounts.dto.AccountRequestdto;
import com.example.accounts.dto.AccountResponseDto;
import com.example.accounts.dto.CustomerRequestDto;
import com.example.accounts.dto.CustomerResponseDto;
import org.springframework.http.ResponseEntity;

public interface IAccount {

    public ResponseEntity<AccountResponseDto> createAccount(CustomerRequestDto customerRequestDto);
     public ResponseEntity<CustomerResponseDto> getCustomerDetails(String mobileNo);
}
