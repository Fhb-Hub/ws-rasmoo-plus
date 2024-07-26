package com.client.ws.rasmooplus.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionTypeDto {
    @JsonProperty(access= JsonProperty.Access.READ_ONLY)
    private Long id;

    private String name;

    private Long accessMonth;

    private BigDecimal price;

    private String productKey;
}
