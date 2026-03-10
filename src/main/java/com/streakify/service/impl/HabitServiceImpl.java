package com.streakify.service.impl;

import com.streakify.dto.HabitRequestDTO;
import com.streakify.dto.HabitResponseDTO;
import com.streakify.entity.Habit;
import com.streakify.entity.User;
import com.streakify.exception.HabitNotFoundException;
import com.streakify.exception.UserNotFoundException;
import com.streakify.repository.HabitRepository;
import com.streakify.repository.UserRepository;
import com.streakify.service.HabitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HabitServiceImpl implements HabitService {

    private final HabitRepository habitRepository;
    private final UserRepository userRepository;

    @Override
    public HabitResponseDTO createHabit(HabitRequestDTO habitRequestDTO) {

        User user = userRepository.findById(habitRequestDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Habit habit = Habit.builder()
                .name(habitRequestDTO.getName())
                .targetDaysPerWeek(habitRequestDTO.getTargetDaysPerWeek())
                .createdAt(LocalDateTime.now())
                .user(user)
                .build();

        Habit savedHabit = habitRepository.save(habit);

        return HabitResponseDTO.builder()
                .id(savedHabit.getId())
                .name(savedHabit.getName())
                .targetDaysPerWeek(savedHabit.getTargetDaysPerWeek())
                .createdAt(savedHabit.getCreatedAt())
                .build();
    }
    @Override
    public List<HabitResponseDTO> getHabitsByUser(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        return habitRepository.findByUserId(userId)
                .stream()
                .map(habit -> HabitResponseDTO.builder()
                        .id(habit.getId())
                        .name(habit.getName())
                        .targetDaysPerWeek(habit.getTargetDaysPerWeek())
                        .createdAt(habit.getCreatedAt())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void deleteHabitById(Long habitId) {

        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() -> new HabitNotFoundException("Habit not found"));

        habitRepository.delete(habit);
    }
}