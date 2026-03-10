package com.streakify.service;

import com.streakify.dto.HabitRequestDTO;
import com.streakify.dto.HabitResponseDTO;

import java.util.List;

public interface HabitService {

    HabitResponseDTO createHabit(HabitRequestDTO habitRequestDTO);

    List<HabitResponseDTO> getHabitsByUser(Long userId);

    void deleteHabitById(Long habitId);
}
