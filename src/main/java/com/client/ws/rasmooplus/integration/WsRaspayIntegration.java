package com.client.ws.rasmooplus.integration;

import com.client.ws.rasmooplus.dto.wsraspay.CustomerDto;
import com.client.ws.rasmooplus.dto.wsraspay.OrderDto;
import com.client.ws.rasmooplus.dto.wsraspay.PaymentDto;
import org.springframework.web.client.RestClientException;

public interface WsRaspayIntegration {

    CustomerDto createCustomer(CustomerDto dto) throws RestClientException;

    OrderDto createOrder(OrderDto dto);

    Boolean processPayment(PaymentDto dto);

}
