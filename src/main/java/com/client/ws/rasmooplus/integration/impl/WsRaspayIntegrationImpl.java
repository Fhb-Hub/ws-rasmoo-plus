package com.client.ws.rasmooplus.integration.impl;

import com.client.ws.rasmooplus.dto.wsraspay.CustomerDto;
import com.client.ws.rasmooplus.dto.wsraspay.OrderDto;
import com.client.ws.rasmooplus.dto.wsraspay.PaymentDto;
import com.client.ws.rasmooplus.integration.WsRaspayIntegration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class WsRaspayIntegrationImpl implements WsRaspayIntegration {

    private final RestTemplate restTemplate;
    private final HttpHeaders headers;

    @Value("${webservices.raspay.host}")
    private String raspayHost;

    @Value("${webservices.raspay.v1.customer}")
    private String customerUrl;

    @Value("${webservices.raspay.v1.order}")
    private String orderUrl;

    @Value("${webservices.raspay.v1.payment}")
    private String paymentUrl;

    public WsRaspayIntegrationImpl() {
        this.restTemplate = new RestTemplate();
        this.headers = getHttpHeaders();
    }

    @Override
    public CustomerDto createCustomer(CustomerDto dto) {
        HttpEntity<CustomerDto> request = new HttpEntity<>(dto, headers);
        ResponseEntity<CustomerDto> response = restTemplate.exchange(raspayHost + customerUrl, HttpMethod.POST, request, CustomerDto.class);

        if (response.getStatusCode() != HttpStatus.CREATED) {
            throw new RestClientException("Unexpected response status: " + response.getStatusCode());
        }

        return response.getBody();
    }

    @Override
    public OrderDto createOrder(OrderDto dto) {
        HttpEntity<OrderDto> request = new HttpEntity<>(dto, headers);
        ResponseEntity<OrderDto> response = restTemplate.exchange(raspayHost + orderUrl, HttpMethod.POST, request, OrderDto.class);

        if (response.getStatusCode() != HttpStatus.CREATED) {
            throw new RestClientException("Unexpected response status: " + response.getStatusCode());
        }

        return response.getBody();
    }

    @Override
    public Boolean processPayment(PaymentDto dto) {
        HttpEntity<PaymentDto> request = new HttpEntity<>(dto, headers);
        ResponseEntity<Boolean> response = restTemplate.exchange(raspayHost + paymentUrl, HttpMethod.POST, request, Boolean.class);
        return response.getBody();
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic cmFzbW9vcGx1czpyQHNtMDA=");
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
