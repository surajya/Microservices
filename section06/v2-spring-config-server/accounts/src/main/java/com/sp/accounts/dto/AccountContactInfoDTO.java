package com.sp.accounts.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "accounts")
public record AccountContactInfoDTO(Map<String, String> message, Map<String, String> contactDetails, Map<String, String> onCallSupport) {
}
