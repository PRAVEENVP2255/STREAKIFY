package com.streakify.controller;

import com.streakify.dto.HabitLogDTO;
import com.streakify.dto.HabitLogRequestDTO;
import com.streakify.dto.HabitLogResponseDTO;
import com.streakify.dto.StreakResponseDTO;
import com.streakify.service.HabitLogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class HabitLogController {

    private final HabitLogService habitLogService;

    @PostMapping("/habits/{habitId}/logs")
    public HabitLogResponseDTO createLog(
            @PathVariable Long habitId,
            @Valid @RequestBody HabitLogRequestDTO request) {

        return habitLogService.createLog(habitId, request);
    }

    @PutMapping("/habits/{habitId}/logs/{date}")
    public HabitLogResponseDTO updateLog(
            @PathVariable Long habitId,
            @PathVariable LocalDate date,
            @Valid @RequestBody HabitLogRequestDTO request) {

        return habitLogService.updateLog(habitId, date, request);
    }

    @GetMapping("/habits/{habitId}/logs")
    public List<HabitLogDTO> getLogs(
            @PathVariable Long habitId) {

        return habitLogService.getLogsByHabit(habitId);
    }

    @GetMapping("/habits/{habitId}/streak")
    public StreakResponseDTO getStreak(
            @PathVariable Long habitId) {

        return habitLogService.calculateStreak(habitId);
    }
}