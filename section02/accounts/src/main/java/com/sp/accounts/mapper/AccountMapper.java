package com.sp.accounts.mapper;

import com.sp.accounts.dto.AccountDto;
import com.sp.accounts.entity.Account;

public class AccountMapper {

    public static AccountDto mapToAccountDto(Account account) {
        return new AccountDto(
                account.getAccountNumber(),
                account.getAccountType(),
                account.getBranchAddress()
        );
    }

    public static Account mapToAccount(AccountDto accountDto) {
        return Account.builder()
                .accountNumber(accountDto.accountNumber())
                .accountType(accountDto.accountType())
                .branchAddress(accountDto.branchAddress())
                .customerId(null)
                .build();
    }
}
