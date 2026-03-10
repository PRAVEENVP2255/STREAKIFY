package com.streakify.service;

import com.streakify.dto.HabitLogDTO;
import com.streakify.dto.HabitLogRequestDTO;
import com.streakify.dto.HabitLogResponseDTO;
import com.streakify.dto.StreakResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface HabitLogService {

    HabitLogResponseDTO createLog(Long habitId, HabitLogRequestDTO request);

    HabitLogResponseDTO updateLog(Long habitId, LocalDate date, HabitLogRequestDTO request);

    List<HabitLogDTO> getLogsByHabit(Long habitId);

    StreakResponseDTO calculateStreak(Long habitId);
}