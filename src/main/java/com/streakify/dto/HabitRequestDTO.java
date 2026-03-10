package com.streakify.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class HabitRequestDTO {

    @NotBlank(message = "Habit name must not be empty")
    private String name;

    @NotNull(message = "Target days per week is required")
    @Min(value = 1, message = "Target days per week must be at least 1")
    @Max(value = 7, message = "Target days per week cannot be more than 7")
    private Integer targetDaysPerWeek;

    @NotNull(message = "UserId is required")
    private Long userId;
}