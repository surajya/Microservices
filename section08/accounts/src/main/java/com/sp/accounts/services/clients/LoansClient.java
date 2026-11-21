package com.sp.accounts.services.clients;

import com.sp.accounts.dto.LoansDto;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange("/api")
public interface LoansClient {

    @GetExchange(value ="/fetch", accept = "application/json")
    ResponseEntity<LoansDto> fetchLoanDetails(@RequestParam("mobileNumber") String mobileNumber);
}
