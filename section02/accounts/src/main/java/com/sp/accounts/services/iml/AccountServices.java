package com.sp.accounts.services.iml;

import com.sp.accounts.constants.AccountConstant;
import com.sp.accounts.dto.CustomerDto;
import com.sp.accounts.entity.Account;
import com.sp.accounts.entity.Customer;
import com.sp.accounts.exception.CustomerAlreadyExistException;
import com.sp.accounts.mapper.CustomerMapper;
import com.sp.accounts.repository.AccountRepository;
import com.sp.accounts.repository.CustomerRepository;
import com.sp.accounts.services.IAccountServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class AccountServices implements IAccountServices {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Optional<Customer> optCustomer = customerRepository.findByMobileNumber(customerDto.mobileNumber());
        if (optCustomer.isPresent()) {
            throw new CustomerAlreadyExistException("Customer with mobile number " + customerDto.mobileNumber() + " already exist");
        }
        Customer savedCustomer = customerRepository.save(CustomerMapper.mapToCustomer(customerDto));
        accountRepository.save(createAccountFromCustomerID(savedCustomer));
    }

    private Account createAccountFromCustomerID(Customer savedCustomer) {
        Long randomNumer = ThreadLocalRandom.current().nextLong(1000000, 10000000);
        Account account = Account.builder()
                .accountNumber(randomNumer)
                .customerId(savedCustomer.getId())
                .accountType(AccountConstant.SAVINGS)
                .branchAddress(AccountConstant.ADDRESS)
                .createdBy(savedCustomer.getCreatedBy())
                .createdAt(LocalDateTime.now())
                .build();
        return account;
    }
}
