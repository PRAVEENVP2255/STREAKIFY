package com.streakify.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class HabitResponseDTO {

    private Long id;
    private String name;
    private Integer targetDaysPerWeek;
    private LocalDateTime createdAt;
}