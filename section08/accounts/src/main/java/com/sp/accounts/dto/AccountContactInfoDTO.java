package com.sp.accounts.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "accounts")
@Getter
@Setter
public class AccountContactInfoDTO {
    private  Map<String, String> message;
    private Map<String, String> contactDetails;
    private Map<String, String> onCallSupport;
}
