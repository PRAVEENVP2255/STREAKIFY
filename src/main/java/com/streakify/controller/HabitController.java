package com.streakify.controller;

import com.streakify.dto.HabitRequestDTO;
import com.streakify.dto.HabitResponseDTO;
import com.streakify.service.HabitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class HabitController {

    private final HabitService habitService;

    @PostMapping("/habits")
    public HabitResponseDTO createHabit(
            @Valid @RequestBody HabitRequestDTO habitRequestDTO) {

        return habitService.createHabit(habitRequestDTO);
    }

    @GetMapping("/users/{userId}/habits")
    public List<HabitResponseDTO> getHabitsByUser(
            @PathVariable Long userId) {

        return habitService.getHabitsByUser(userId);
    }

    @DeleteMapping("/habits/{id}")
    public Map<String, String> deleteHabit(
            @PathVariable Long id) {

        habitService.deleteHabitById(id);

        return Map.of("message", "Habit deleted successfully");
    }
}