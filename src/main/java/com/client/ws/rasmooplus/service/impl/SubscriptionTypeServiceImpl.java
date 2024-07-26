package com.client.ws.rasmooplus.service.impl;

import com.client.ws.rasmooplus.dto.SubscriptionTypeDto;
import com.client.ws.rasmooplus.exception.NotFoundException;
import com.client.ws.rasmooplus.model.SubscriptionType;
import com.client.ws.rasmooplus.repositoy.SubscriptionTypeRepository;
import com.client.ws.rasmooplus.service.SubscriptionTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SubscriptionTypeServiceImpl implements SubscriptionTypeService {
    private final SubscriptionTypeRepository subscriptionTypeRepository;

    SubscriptionTypeServiceImpl(SubscriptionTypeRepository subscriptionTypeRepository) {
        this.subscriptionTypeRepository = subscriptionTypeRepository;
    }

    @Override
    public List<SubscriptionType> findAll() {
        return subscriptionTypeRepository.findAll();
    }

    @Override
    public SubscriptionType findById(Long id) {
        Optional<SubscriptionType> optionalSubscriptionType = subscriptionTypeRepository.findById(id);
        return optionalSubscriptionType.orElseThrow(() -> new NotFoundException("SubscriptionType não encontrado"));
    }

    @Override
    public SubscriptionType create(SubscriptionTypeDto dto) {
        return subscriptionTypeRepository.save(SubscriptionType.builder()
                .id(dto.getId())
                .name(dto.getName() )
                .accessMonth(dto.getAccessMonth())
                .price(dto.getPrice())
                .productKey(dto.getProductKey())
                .build());
    }

    @Override
    public SubscriptionType update(Long id, SubscriptionType subscriptionType) {
        if (findById(id) == null) {
            return null;
        }
        return subscriptionTypeRepository.save(subscriptionType);
    }

    @Override
    public void delete(Long id) {
        SubscriptionType subscriptionType = findById(id);

        if (Objects.nonNull(subscriptionType)) {
            subscriptionTypeRepository.delete(subscriptionType);
        }
    }
}
