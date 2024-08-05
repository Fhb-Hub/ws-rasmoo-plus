package com.client.ws.rasmooplus.integration;

import com.client.ws.rasmooplus.dto.wsraspay.CreditCardDto;
import com.client.ws.rasmooplus.dto.wsraspay.CustomerDto;
import com.client.ws.rasmooplus.dto.wsraspay.OrderDto;
import com.client.ws.rasmooplus.dto.wsraspay.PaymentDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class WsRaspayIntegrationImplTest {

    @Autowired
    private WsRaspayIntegration wsRaspayIntegration;

    @Test
    void createCustomerWhenDtoOK() {
        CustomerDto dto = new CustomerDto(null, "02371386030", "teste@teste", "Fellipe", "Bordon");
        wsRaspayIntegration.createCustomer(dto);
    }

    @Test
    void createOrderWhenDtoOK() {
        OrderDto dto = new OrderDto(null, "6411afccc003da38e375b437", BigDecimal.ZERO, "MONTH22");
        wsRaspayIntegration.createOrder(dto);
    }

    @Test
    void processPaymentWhenDtoOK() {
        CreditCardDto creditCardDto = new CreditCardDto(123L, "02371386030", 0L, 06L, "1234123412341234", 2025L);
        PaymentDto paymentDto = new PaymentDto(creditCardDto, "6411afccc003da38e375b437", "66b0dc74d4312606d83a18f0");
        wsRaspayIntegration.processPayment(paymentDto);
    }
}