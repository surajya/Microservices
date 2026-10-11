package com.sp.accounts.mapper;

import com.sp.accounts.dto.AccountDto;
import com.sp.accounts.dto.CustomerDto;
import com.sp.accounts.entity.Customer;

import java.time.LocalDateTime;

public class CustomerMapper {

    public static CustomerDto mapToCustomerDto(Customer customer, AccountDto accountDto) {
        return new CustomerDto(
                customer.getName(),
                customer.getEmail(),
                customer.getMobileNumber(),
                accountDto
        );
    }

    public static Customer mapToCustomer(CustomerDto customerDto) {
        return Customer.builder()
                .name(customerDto.name())
                .email(customerDto.email())
                .mobileNumber(customerDto.mobileNumber())
                .createdBy("Sohan Sharma")
                .createdAt(LocalDateTime.now())
                .build();
    }
}
