package com.example.accounts.Mapper;

import com.example.accounts.AppConstants;
import com.example.accounts.Entity.Account;
import com.example.accounts.Entity.Customer;
import com.example.accounts.Entity.Users;
import com.example.accounts.dto.*;

public class Mapperto {
  public static Account toAccount(AccountRequestdto accountRequestdto){
      Account account = new Account();
      account.setAccountType(accountRequestdto.getAccountType());
      account.setBranchAddress(accountRequestdto.getBranchAddress());
      return account;
  }
  public static AccountResponseDto toAccountResponseDto(Account account){
      AccountResponseDto accountResponseDto=new AccountResponseDto();
      accountResponseDto.setStatus(AppConstants.CREATED_CODE);
      accountResponseDto.setMessage(AppConstants.CREATED_MESSAGE);
     return accountResponseDto;
  }
  public static CustomerResponseDto toCustomerResponseDto(Customer customer, Account account){
      CustomerResponseDto customerResponseDto=new CustomerResponseDto();
      customerResponseDto.setStatus(AppConstants.CREATED_CODE);
      customerResponseDto.setMessage(AppConstants.CREATED_MESSAGE);
      customerResponseDto.setFirstName(customer.getFirstName());
      customerResponseDto.setLastName(customer.getLastName());
      customerResponseDto.setEmail(customer.getEmail());
      customerResponseDto.setPhoneNumber(customer.getPhoneNumber());
      customerResponseDto.setAddress(customer.getAddress());
      customerResponseDto.setCity(customer.getCity());
      customerResponseDto.setCountry(customer.getCountry());
      customerResponseDto.setState(customer.getState());
      customerResponseDto.setPostalCode(customer.getState());
      customerResponseDto.setPostalCode(customer.getZipCode());
      customerResponseDto.setAccount(account);
      return customerResponseDto;
  }
  public static Customer toCustomer(CustomerRequestDto customerRequestDto){
      Customer customer=new Customer();
      customer.setFirstName(customerRequestDto.getFirstName());
      customer.setLastName(customerRequestDto.getLastName());
      customer.setEmail(customerRequestDto.getEmail());
      customer.setAddress(customerRequestDto.getAddress());
      customer.setCity(customerRequestDto.getCity());
      customer.setState(customerRequestDto.getState());
      customer.setZipCode(customerRequestDto.getZipCode());
      customer.setCountry(customerRequestDto.getCountry());
      customer.setPhoneNumber(customerRequestDto.getPhoneNumber());
      return customer;
  }
  public static Users toUser(UserDto userDto) {
      Users user = new Users();
      user.setFirstName(userDto.getFirstName());
      user.setLastName(userDto.getLastName());
      user.setEmail(userDto.getEmail());
      user.setPassword(userDto.getPassword());
      user.setMobileNo(userDto.getMobileNo());
      user.setRole(userDto.getRole());
      return user;
  }
  public static UserResponsedto toUserResponseDto(Users user) {
      UserResponsedto userResponseDto=new UserResponsedto();
      userResponseDto.setMessage(AppConstants.CREATED_MESSAGE);
      userResponseDto.setStatus(AppConstants.CREATED_CODE);
      return  userResponseDto;
  }
}
