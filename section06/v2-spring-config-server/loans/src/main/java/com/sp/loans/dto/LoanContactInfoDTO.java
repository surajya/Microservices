package com.sp.loans.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "loans")
@Getter
@Setter
public class LoanContactInfoDTO {
    private  Map<String, String> message;
    private Map<String, String> contactDetails;
    private Map<String, String> onCallSupport;
}