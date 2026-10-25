package com.example.accounts.controller;

import com.example.accounts.Service.IAccount;
import com.example.accounts.dto.AccountResponseDto;
import com.example.accounts.dto.CustomerRequestDto;
import com.example.accounts.dto.CustomerResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/account")
public class Account {

    final private IAccount account;
    public Account(IAccount account) {
        this.account = account;
    }
    @PostMapping("/create")
    public ResponseEntity<AccountResponseDto> createAccount(@RequestBody CustomerRequestDto customerRequestDto) {
        return account.createAccount(customerRequestDto);
    }
   @GetMapping("/fetch/{Id}")
    public ResponseEntity<CustomerResponseDto> getAccountById(@PathVariable("Id") String accountNumber) {
        return account.getCustomerDetails(accountNumber);

   }


}
