package com.client.ws.rasmooplus.service;

import com.client.ws.rasmooplus.dto.UserDto;
import com.client.ws.rasmooplus.model.User;

import java.util.List;

public interface UserService {

    User create(UserDto dto);

    List<UserDto> findAll();

    UserDto findById(Long id);
}
