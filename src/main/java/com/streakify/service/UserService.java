package com.streakify.service;

import com.streakify.dto.UserRequestDTO;
import com.streakify.dto.UserResponseDTO;

public interface UserService {

    UserResponseDTO createUser(UserRequestDTO userRequestDTO);

    UserResponseDTO getUserById(Long id);

    void deleteUser(Long id);
}
