package com.sp.cards.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "cards")
public record CardContactInfoDTO(Map<String, String> message, Map<String, String> contactDetails, Map<String, String> onCallSupport) {
}
