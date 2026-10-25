package com.example.accounts.Exception;

import com.example.accounts.AppConstants;
import com.example.accounts.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandller {
    @ExceptionHandler(CustomerAlreadyExist.class)
    public ResponseEntity<ErrorResponse> handleException(CustomerAlreadyExist customerAlreadyExist, WebRequest request) {
       ErrorResponse errorResponse=new ErrorResponse() ;
        errorResponse.setMessage(customerAlreadyExist.getMessage());
        errorResponse.setApitPath(request.getDescription(false));
        errorResponse.setErrorDate(LocalDateTime.now());
        errorResponse.setStatus(AppConstants.BAD_REQUEST_CODE);

         return ResponseEntity.badRequest().body(errorResponse);

    }
    @ExceptionHandler(ResourceNotFounException.class)
    public ResponseEntity<ErrorResponse> resourceNotFounException(ResourceNotFounException resourceNotFounException, WebRequest request) {
        ErrorResponse errorResponse=new ErrorResponse() ;
        errorResponse.setMessage(resourceNotFounException.getMessage());
        errorResponse.setApitPath(request.getDescription(false));
        errorResponse.setErrorDate(LocalDateTime.now());
        errorResponse.setStatus(AppConstants.NOT_FOUND_CODE);

        return ResponseEntity.badRequest().body(errorResponse);

    }
}
