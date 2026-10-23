package com.sp.accounts.services;

import com.sp.accounts.dto.CustomerDto;

public interface IAccountServices {

    void createAccount(CustomerDto customerDto);

    CustomerDto findAccount(String mobileNumber);

    boolean updateAccount(CustomerDto customerDto);

    boolean deleteAccount(String mobileNumber);
}
