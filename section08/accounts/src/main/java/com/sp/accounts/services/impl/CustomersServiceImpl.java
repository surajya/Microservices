package com.sp.accounts.services.impl;



import com.sp.accounts.dto.CardsDto;
import com.sp.accounts.dto.CustomerDetailsDto;
import com.sp.accounts.dto.LoansDto;
import com.sp.accounts.entity.Account;
import com.sp.accounts.entity.Customer;
import com.sp.accounts.exception.ResourceNotFoundException;
import com.sp.accounts.mapper.AccountMapper;
import com.sp.accounts.mapper.CustomerMapper;
import com.sp.accounts.repository.AccountRepository;
import com.sp.accounts.repository.CustomerRepository;
import com.sp.accounts.services.ICustomersService;
import com.sp.accounts.services.clients.CardsClient;
import com.sp.accounts.services.clients.LoansClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomersServiceImpl implements ICustomersService {

    private AccountRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsClient cardsClient;
    private LoansClient loansClient;


    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Account accounts = accountsRepository.findAccountByCustomerId(customer.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getId().toString())
        );

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountMapper.mapToAccountDto(accounts));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansClient.fetchLoanDetails(mobileNumber);
        customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsClient.fetchCardDetails(mobileNumber);
        customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());

        return customerDetailsDto;

    }
}
