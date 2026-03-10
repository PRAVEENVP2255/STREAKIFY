package com.streakify.service.impl;

import com.streakify.dto.UserRequestDTO;
import com.streakify.dto.UserResponseDTO;
import com.streakify.entity.User;
import com.streakify.exception.DuplicateEmailException;
import com.streakify.exception.UserNotFoundException;
import com.streakify.repository.UserRepository;
import com.streakify.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {

        User user = User.builder()
                .name(userRequestDTO.getName())
                .email(userRequestDTO.getEmail())
                .createdAt(LocalDateTime.now())
                .build();

        try {
            User savedUser = userRepository.save(user);

            return UserResponseDTO.builder()
                    .id(savedUser.getId())
                    .name(savedUser.getName())
                    .email(savedUser.getEmail())
                    .createdAt(savedUser.getCreatedAt())
                    .build();

        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateEmailException("Email already exists");
        }
    }

    @Override
    public UserResponseDTO getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .build();
    }

    @Override
    public void deleteUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        userRepository.delete(user);
    }
}