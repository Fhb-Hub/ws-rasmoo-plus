package com.client.ws.rasmooplus.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "Não pode ser nulo ou vazio")
    @Size(min = 6, message = "Mínimo igual a 6 caracteres")
    private String name;

    @Email(message = "Inválido")
    private String email;

    @Size(min = 11, message = "deve possuir no mínimo 11 dígitos")
    private String phone;

    @CPF(message = "Inválido")
    private String cpf;

    private LocalDate dtSubscription = LocalDate.now();

    private LocalDate dtExpiration = LocalDate.now();

    @NotNull(message = "Não pode ser nulo")
    private Long userTypeId;

    private Long subscriptionTypeId;
}