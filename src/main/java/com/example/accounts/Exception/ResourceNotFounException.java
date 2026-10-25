package com.example.accounts.Exception;

public class ResourceNotFounException extends RuntimeException {
    public ResourceNotFounException(String resourceName,String fieldNmae,String fieldValue) {
        super(String.format("Resource not foun for %s %s %s",resourceName, fieldNmae, fieldValue));

    }
}
