package com.example.accounts.Exception;

public class CustomerAlreadyExist extends RuntimeException{
    public CustomerAlreadyExist(String message){
        super(message);
    }
}
