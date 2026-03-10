package com.streakify.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardResponseDTO {

    private int totalHabits;
    private int activeHabits;
    private int completedToday;
    private List<StreakSummaryDTO> currentStreaks;
    private int consistencyScore;
}