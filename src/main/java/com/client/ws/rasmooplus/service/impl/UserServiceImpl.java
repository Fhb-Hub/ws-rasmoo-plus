package com.client.ws.rasmooplus.service.impl;

import com.client.ws.rasmooplus.dto.UserDto;
import com.client.ws.rasmooplus.exception.NotFoundException;
import com.client.ws.rasmooplus.mapper.UserMapper;
import com.client.ws.rasmooplus.model.User;
import com.client.ws.rasmooplus.model.UserType;
import com.client.ws.rasmooplus.repositoy.UserRepository;
import com.client.ws.rasmooplus.repositoy.UserTypeRepository;
import com.client.ws.rasmooplus.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserTypeRepository userTypeRepository;

    public UserServiceImpl(UserRepository userRepository, UserTypeRepository userTypeRepository) {
        this.userRepository = userRepository;
        this.userTypeRepository = userTypeRepository;
    }

    @Override
    public User create(UserDto dto) {
        UserType userType = userTypeRepository.findById(dto.getUserTypeId())
                .orElseThrow(() -> new NotFoundException("userTypeId não encontrado"));

        User user = UserMapper.fromDtoToEntity(dto, userType, null);

        return userRepository.save(user);
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(UserMapper::fromEntityToDto).collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Long id) {
        return UserMapper.fromEntityToDto(userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado")));
    }
}
