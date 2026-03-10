package com.streakify.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StreakSummaryDTO {

    private String habitName;
    private int currentStreak;
    private int longestStreak;
}