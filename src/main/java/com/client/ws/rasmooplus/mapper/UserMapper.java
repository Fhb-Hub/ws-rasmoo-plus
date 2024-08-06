package com.client.ws.rasmooplus.mapper;

import com.client.ws.rasmooplus.dto.UserDto;
import com.client.ws.rasmooplus.model.SubscriptionType;
import com.client.ws.rasmooplus.model.User;
import com.client.ws.rasmooplus.model.UserType;

public class UserMapper {

    public static User fromDtoToEntity(UserDto dto, UserType userType, SubscriptionType subscriptionType) {
        return com.client.ws.rasmooplus.model.User.builder()
                .id(dto.getId())
                .name(dto.getName())
                .cpf(dto.getCpf())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .dtSubscription(dto.getDtSubscription())
                .dtExpiration(dto.getDtExpiration())
                .userType(userType)
                .subscriptionType(subscriptionType)
                .build();
    }

    public static UserDto fromEntityToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .cpf(user.getCpf())
                .dtSubscription(user.getDtSubscription())
                .dtExpiration(user.getDtExpiration())
                .userTypeId(user.getUserType() != null ? user.getUserType().getId() : null)
                .subscriptionTypeId(user.getSubscriptionType() != null ? user.getSubscriptionType().getId() : null)
                .build();
    }
}
