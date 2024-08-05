package com.client.ws.rasmooplus.service.impl;

import com.client.ws.rasmooplus.dto.PaymentProcessDto;
import com.client.ws.rasmooplus.dto.wsraspay.CustomerDto;
import com.client.ws.rasmooplus.dto.wsraspay.OrderDto;
import com.client.ws.rasmooplus.dto.wsraspay.PaymentDto;
import com.client.ws.rasmooplus.exception.BusinessException;
import com.client.ws.rasmooplus.integration.WsRaspayIntegration;
import com.client.ws.rasmooplus.mapper.UserPaymentInfoMapper;
import com.client.ws.rasmooplus.mapper.wsraspay.CreditCardMapper;
import com.client.ws.rasmooplus.mapper.wsraspay.CustomerMapper;
import com.client.ws.rasmooplus.mapper.wsraspay.OrderMapper;
import com.client.ws.rasmooplus.mapper.wsraspay.PaymentMapper;
import com.client.ws.rasmooplus.model.User;
import com.client.ws.rasmooplus.model.UserPaymentInfo;
import com.client.ws.rasmooplus.repositoy.UserPaymentInfoRepository;
import com.client.ws.rasmooplus.service.PaymentInfoService;
import com.client.ws.rasmooplus.service.UserService;

import java.util.Objects;

public class PaymentInfoServiceImpl implements PaymentInfoService {
    private final UserService userService;
    private final WsRaspayIntegration wsRaspayIntegration;
    private final UserPaymentInfoRepository userPaymentInfoRepository;

    public PaymentInfoServiceImpl(UserService userService, WsRaspayIntegration wsRaspayIntegration,
            UserPaymentInfoRepository userPaymentInfoRepository) {
        this.userService = userService;
        this.wsRaspayIntegration = wsRaspayIntegration;
        this.userPaymentInfoRepository = userPaymentInfoRepository;
    }

    @Override
    public Boolean process(PaymentProcessDto dto) {
        User user = userService.findById(dto.getUserPaymentInfoDto().getId());

        if (Objects.nonNull(user.getSubscriptionType())) {
            throw new BusinessException("Pagamento não pode ser processado pois usuário já possui assinatura");
        }

        CustomerDto customerDto = wsRaspayIntegration.createCustomer(CustomerMapper.build(user));

        OrderDto orderDto = wsRaspayIntegration.createOrder(OrderMapper.build(customerDto.getId(), dto));

        PaymentDto paymentDto = PaymentMapper.build(customerDto.getId(), orderDto.getId(), CreditCardMapper.build(dto.getUserPaymentInfoDto(), user.getCpf()));
        Boolean processPaymentSuccess = wsRaspayIntegration.processPayment(paymentDto);

        if (processPaymentSuccess) {
            UserPaymentInfo userPaymentInfo = UserPaymentInfoMapper.fromDtoToEntity(dto.getUserPaymentInfoDto(), user);
            userPaymentInfoRepository.save(userPaymentInfo);
        }

        // TODO: enviar email de criacao de conta

        return processPaymentSuccess;
    }
}
