package com.client.ws.rasmooplus.service.impl;

import com.client.ws.rasmooplus.controller.SubscriptionTypeController;
import com.client.ws.rasmooplus.dto.SubscriptionTypeDto;
import com.client.ws.rasmooplus.exception.NotFoundException;
import com.client.ws.rasmooplus.mapper.SubscriptionTypeMapper;
import com.client.ws.rasmooplus.model.SubscriptionType;
import com.client.ws.rasmooplus.repositoy.SubscriptionTypeRepository;
import com.client.ws.rasmooplus.service.SubscriptionTypeService;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionTypeServiceImpl implements SubscriptionTypeService {
    private static final String UPDATE = "update";
    private static final String DELETE = "delete";

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
        SubscriptionType subscriptionType = subscriptionTypeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("SubscriptionType não encontrado"));

        subscriptionType.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SubscriptionTypeController.class)
                .findById(id)).withSelfRel());

        subscriptionType.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SubscriptionTypeController.class)
                .update(id, new SubscriptionTypeDto())).withRel(UPDATE));

        subscriptionType.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SubscriptionTypeController.class)
                .delete(id)).withRel(DELETE));

        return subscriptionType;
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
