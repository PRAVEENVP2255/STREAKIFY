package com.streakify.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StreakResponseDTO {

    private int currentStreak;
    private int longestStreak;
}