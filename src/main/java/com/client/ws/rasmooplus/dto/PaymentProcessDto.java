package com.client.ws.rasmooplus.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentProcessDto {
    @NotBlank(message = "deve ser informado")
    private String productKey;

    private BigDecimal discount;

    @NotNull(message = "dados do pagamento deve ser informado")
    @JsonProperty("userPaymentInfo")
    private UserPaymentInfoDto userPaymentInfoDto;
}
