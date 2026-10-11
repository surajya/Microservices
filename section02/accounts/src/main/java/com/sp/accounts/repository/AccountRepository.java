package com.sp.accounts.repository;

import com.sp.accounts.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findAccountByCustomerId(Long customerId);

    Optional<Account> findByAccountNumber(Long aLong);

    void deleteByAccountNumber(Long accountNumber);
}
