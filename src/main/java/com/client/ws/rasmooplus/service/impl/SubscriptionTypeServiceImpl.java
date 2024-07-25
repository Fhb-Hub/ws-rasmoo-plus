package com.client.ws.rasmooplus.service.impl;

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
        Optional<SubscriptionType> optionalSubscriptionType  = subscriptionTypeRepository.findById(id);
        return optionalSubscriptionType.orElse(null);
    }

    @Override
    public SubscriptionType create(SubscriptionType subscriptionType) {
        return subscriptionTypeRepository.save(subscriptionType);
    }

    @Override
    public SubscriptionType update(Long id, SubscriptionType subscriptionType) {
        if(findById(id) == null) {
            return null;
        }
        return subscriptionTypeRepository.save(subscriptionType);
    }

    @Override
    public void delete(Long id) {
        SubscriptionType subscriptionType = findById(id);

        if(Objects.nonNull(subscriptionType)) {
            subscriptionTypeRepository.delete(subscriptionType);
        }
    }
}
