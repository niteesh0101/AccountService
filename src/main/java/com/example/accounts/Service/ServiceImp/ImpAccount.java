package com.example.accounts.Service.ServiceImp;

import com.example.accounts.AppConstants;
import com.example.accounts.Entity.Account;
import com.example.accounts.Entity.Customer;
import com.example.accounts.Exception.CustomerAlreadyExist;
import com.example.accounts.Exception.ResourceNotFounException;
import com.example.accounts.Mapper.Mapperto;
import com.example.accounts.Repository.AccountsRepository;
import com.example.accounts.Repository.CustomerRepository;
import com.example.accounts.Service.IAccount;
import com.example.accounts.dto.AccountRequestdto;
import com.example.accounts.dto.AccountResponseDto;
import com.example.accounts.dto.CustomerRequestDto;
import com.example.accounts.dto.CustomerResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
@Service
public class ImpAccount implements IAccount {
    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;
    public ImpAccount(AccountsRepository accountsRepository, CustomerRepository customerRepository) {
        this.accountsRepository = accountsRepository;
        this.customerRepository = customerRepository;
    }
    @Override
    public ResponseEntity<AccountResponseDto> createAccount(CustomerRequestDto customerRequestDto) {
       Customer customer=Mapperto.toCustomer(customerRequestDto);


        Optional<Customer> exist=customerRepository.findByEmail(customerRequestDto.getEmail());
        if(exist.isPresent()){
            throw  new CustomerAlreadyExist("customer already exist with "+customerRequestDto.getEmail());

        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Alien");
        Customer saved=customerRepository.save(customer);

        Account account= createAc(saved);
                accountsRepository.save(account);

        return ResponseEntity.ok().body(Mapperto.toAccountResponseDto(account));
    }



    public static Account createAc(Customer  customer){
        Account account=new Account();
        account.setCustomerId(customer.getCustomerId());
        account.setBranchAddress(customer.getAddress() + " " + customer.getCity()+ " " + customer.getCountry());
        Long radnoAccountNumber=100085L+new Random().nextInt(1000);
        account.setAccountNumber(radnoAccountNumber);
        account.setAccountType("Savings");
        account.setCreatedAt(LocalDateTime.now());
        account.setCreatedBy("Alien");

        return account;
    }

    //Method for finding the Accountant and AccountDetails using MobileNo

    @Override
    public ResponseEntity<CustomerResponseDto> getCustomerDetails(String mobileNo) {
        Customer customer=customerRepository.findByphoneNumber(mobileNo).orElseThrow(()->new ResourceNotFounException("Customer Details","With mobile no not","found"+mobileNo));
        Account account=accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(()->new ResourceNotFounException("Account Details","With customer id not","found"+customer.getCustomerId()));
   return ResponseEntity.status(HttpStatus.OK).body(Mapperto.toCustomerResponseDto(customer,account));
    }
}
