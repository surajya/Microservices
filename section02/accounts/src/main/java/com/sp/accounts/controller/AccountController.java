package com.sp.accounts.controller;

import com.sp.accounts.constants.AccountConstant;
import com.sp.accounts.dto.CustomerDto;
import com.sp.accounts.dto.ResponseDto;
import com.sp.accounts.services.IAccountServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AccountController {

    private final IAccountServices accountServices;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> CreateAccount(@RequestBody CustomerDto customerDto) {
        accountServices.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstant.STATUS_201, AccountConstant.MESSAGE_201));
    }
}
