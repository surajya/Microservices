package com.sp.cards.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "cards")
@Getter
@Setter
public class CardContactInfoDTO {
    private  Map<String, String> message;
    private Map<String, String> contactDetails;
    private Map<String, String> onCallSupport;
}
