package com.streakify.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class HabitLogRequestDTO {

    @NotNull(message = "Log date is required")
    private LocalDate logDate;

    @NotNull(message = "Completed status is required")
    private Boolean completed;
}