package com.streakify.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class HabitLogResponseDTO {

    private Long id;
    private Long habitId;
    private LocalDate logDate;
    private Boolean completed;
    private String weeklyStatus;
}