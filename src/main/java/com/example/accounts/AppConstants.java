package com.example.accounts;

import lombok.Data;

@Data
public final class AppConstants {

    private AppConstants() {}

    // Success
    public static final int SUCCESS_CODE = 200;
    public static final String SUCCESS_MESSAGE = "Request processed successfully";

    // Created
    public static final int CREATED_CODE = 201;
    public static final String CREATED_MESSAGE = "Resource created successfully";

    // Bad Request
    public static final int BAD_REQUEST_CODE = 400;
    public static final String BAD_REQUEST_MESSAGE = "Invalid request";

    // Unauthorized
    public static final int UNAUTHORIZED_CODE = 401;
    public static final String UNAUTHORIZED_MESSAGE = "Authentication required";

    // Forbidden
    public static final int FORBIDDEN_CODE = 403;
    public static final String FORBIDDEN_MESSAGE = "Access denied";

    // Not Found
    public static final int NOT_FOUND_CODE = 404;
    public static final String NOT_FOUND_MESSAGE = "Resource not found";

    // Conflict
    public static final int CONFLICT_CODE = 409;
    public static final String CONFLICT_MESSAGE = "Resource already exists";

    // Internal Server Error
    public static final int SERVER_ERROR_CODE = 500;
    public static final String SERVER_ERROR_MESSAGE = "Something went wrong";
    public static final String USER_CREATED = "User created successfully";
    public static final String USER_UPDATED = "User updated successfully";
    public static final String USER_NOT_FOUND = "User not found";

    public static final String ACCOUNT_CREATED = "Account created successfully";
    public static final String ACCOUNT_NOT_FOUND = "Account not found";
}