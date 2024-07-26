package com.client.ws.rasmooplus.service.impl;

import com.client.ws.rasmooplus.dto.SubscriptionTypeDto;
import com.client.ws.rasmooplus.exception.NotFoundException;
import com.client.ws.rasmooplus.mapper.SubscriptionTypeMapper;
import com.client.ws.rasmooplus.model.SubscriptionType;
import com.client.ws.rasmooplus.repositoy.SubscriptionTypeRepository;
import com.client.ws.rasmooplus.service.SubscriptionTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
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
        return save(dto);
    }

    @Override
    public SubscriptionType update(Long id, SubscriptionTypeDto dto) {
        findById(id);
        dto.setId(id);
        return save(dto);
    }

    @Override
    public void delete(Long id) {
        findById(id);
        subscriptionTypeRepository.deleteById(id);
    }

    private SubscriptionType save(SubscriptionTypeDto dto) {
        return subscriptionTypeRepository.save(SubscriptionTypeMapper.fromDtoToEntity(dto));
    }
}
