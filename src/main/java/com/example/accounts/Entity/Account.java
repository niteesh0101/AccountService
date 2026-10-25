package com.example.accounts.Entity;

import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Account extends BaseEntity {
    @NonNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    private Long accountNumber;
    private String AccountType;
    private String BranchAddress;

}
