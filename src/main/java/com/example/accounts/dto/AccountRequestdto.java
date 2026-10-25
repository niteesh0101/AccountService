package com.example.accounts.dto;

import lombok.Data;
import lombok.NonNull;
@Data
public class AccountRequestdto {
    @NonNull
    private String accountNumber;
    @NonNull
    private String AccountType;
    @NonNull
    private String BranchAddress;
}
