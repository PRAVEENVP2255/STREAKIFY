package com.streakify.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HabitLogDTO {

    private Long id;
    private Long habitId;
    private LocalDate logDate;
    private Boolean completed;
}
