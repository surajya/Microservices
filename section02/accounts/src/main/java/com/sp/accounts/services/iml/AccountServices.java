package com.sp.accounts.services.iml;

import com.sp.accounts.constants.AccountConstant;
import com.sp.accounts.dto.AccountDto;
import com.sp.accounts.dto.CustomerDto;
import com.sp.accounts.entity.Account;
import com.sp.accounts.entity.Customer;
import com.sp.accounts.exception.CustomerAlreadyExistException;
import com.sp.accounts.exception.ResourceNotFoundException;
import com.sp.accounts.mapper.AccountMapper;
import com.sp.accounts.mapper.CustomerMapper;
import com.sp.accounts.repository.AccountRepository;
import com.sp.accounts.repository.CustomerRepository;
import com.sp.accounts.services.IAccountServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public CustomerDto findAccount(String mobileNumber) {
        Optional<Customer> optCustomer = customerRepository.findByMobileNumber(mobileNumber);
        if (optCustomer.isEmpty()) {
            throw new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber);
        }
        Customer customer = optCustomer.get();
        Optional<Account> account = accountRepository.findAccountByCustomerId(customer.getId());
        if (account.isEmpty()) {
            throw new ResourceNotFoundException("Account", "customerID", customer.getId().toString());
        }
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, AccountMapper.mapToAccountDto(account.get()));
        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        Boolean isUpdated = false;
        AccountDto accountDto = customerDto.accountDto();
        if (accountDto != null) {
            Account account = accountRepository.findByAccountNumber(accountDto.accountNumber()).orElseThrow(() ->
                    new ResourceNotFoundException("Account", "AccountNumber", accountDto.accountNumber().toString())
            );

            BeanUtils.copyProperties(accountDto, account);
            account.setUpdatedAt(LocalDateTime.now());
            account.setUpdatedBy("Ramu Das Yadav");
            accountRepository.save(account);

            Customer customer = customerRepository.findById(account.getCustomerId()).orElseThrow(() ->
                    new ResourceNotFoundException("Customer", "CustomerId", account.getCustomerId().toString())
            );

            BeanUtils.copyProperties(customerDto, customer);
            customer.setUpdatedAt(LocalDateTime.now());
            customer.setUpdatedBy("Ramu Das Yadav");
            customerRepository.save(customer);
            isUpdated = true;
        }

        return isUpdated;
    }

    @Override
    @Transactional
    public boolean deleteAccount(String mobileNumber) {
        boolean isUpdated = false;
        Optional<Customer> optCustomer = customerRepository.findByMobileNumber(mobileNumber);
        if (optCustomer.isEmpty()) {
            throw new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber);
        }
        Customer customer = optCustomer.get();
        Optional<Account> account = accountRepository.findAccountByCustomerId(customer.getId());
        if (account.isEmpty()) {
            throw new ResourceNotFoundException("Account", "customerID", customer.getId().toString());
        }
        accountRepository.deleteByAccountNumber(account.get().getAccountNumber());
        customerRepository.deleteById(account.get().getCustomerId());
        isUpdated = true;
        return isUpdated;
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
