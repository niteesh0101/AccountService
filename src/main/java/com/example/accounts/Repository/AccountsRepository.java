package com.example.accounts.Repository;

import com.example.accounts.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountsRepository extends JpaRepository<Account,Long> {
    Optional<Account> findByCustomerId(Long customerId);
}
