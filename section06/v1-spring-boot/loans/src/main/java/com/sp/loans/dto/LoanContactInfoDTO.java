package com.sp.loans.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "loans")
public record LoanContactInfoDTO(Map<String, String> message, Map<String, String> contactDetails, Map<String, String> onCallSupport) {
}