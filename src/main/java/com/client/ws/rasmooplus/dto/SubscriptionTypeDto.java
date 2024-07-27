package com.client.ws.rasmooplus.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "Não pode ser nulo ou vazio")
    @Size(min = 5, max = 30, message = "deve ter tamanho entre 5 e 30")
    private String name;

    @Max(value = 12, message = "Não pode ser maior que 12")
    private Long accessMonths;

    @NotNull(message = "Não pode ser nulo")
    private BigDecimal price;

    @NotBlank(message = "Não pode ser nulo ou vazio")
    @Size(min = 5, max = 15, message = "Deve ter tamanho entre 5 e 15")
    private String productKey;
}
