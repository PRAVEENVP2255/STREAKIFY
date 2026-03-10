package com.streakify.service.impl;

import com.streakify.dto.DashboardResponseDTO;
import com.streakify.dto.StreakResponseDTO;
import com.streakify.dto.StreakSummaryDTO;
import com.streakify.entity.Habit;
import com.streakify.entity.HabitLog;
import com.streakify.exception.UserNotFoundException;
import com.streakify.repository.HabitLogRepository;
import com.streakify.repository.HabitRepository;
import com.streakify.repository.UserRepository;
import com.streakify.service.DashboardService;
import com.streakify.service.HabitLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final UserRepository userRepository;
    private final HabitRepository habitRepository;
    private final HabitLogRepository habitLogRepository;
    private final HabitLogService habitLogService;

    @Override
    public DashboardResponseDTO getDashboard(Long userId) {

        userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        List<Habit> habits = habitRepository.findByUserId(userId);

        int totalHabits = habits.size();

        int activeHabits = (int) habits.stream()
                .filter(habit -> !habitLogRepository.findByHabitId(habit.getId()).isEmpty())
                .count();

        LocalDate today = LocalDate.now();

        int completedToday = (int) habits.stream()
                .flatMap(habit -> habitLogRepository.findByHabitId(habit.getId()).stream())
                .filter(log -> log.getLogDate().equals(today))
                .filter(HabitLog::getCompleted)
                .count();

        List<StreakSummaryDTO> streakSummaries = habits.stream()
                .map(habit -> {
                    StreakResponseDTO streak = habitLogService.calculateStreak(habit.getId());
                    return StreakSummaryDTO.builder()
                            .habitName(habit.getName())
                            .currentStreak(streak.getCurrentStreak())
                            .longestStreak(streak.getLongestStreak())
                            .build();
                })
                .collect(Collectors.toList());

        int consistencyScore = calculateWeeklyConsistency(userId);

        return DashboardResponseDTO.builder()
                .totalHabits(totalHabits)
                .activeHabits(activeHabits)
                .completedToday(completedToday)
                .currentStreaks(streakSummaries)
                .consistencyScore(consistencyScore)
                .build();
    }

    private int calculateWeeklyConsistency(Long userId) {

        List<Habit> habits = habitRepository.findByUserId(userId);
        if (habits.isEmpty()) return 0;

        LocalDate today = LocalDate.now();

        LocalDate startOfWeek = today.minusDays(today.getDayOfWeek().getValue() % 7);

        long daysPassed =
                ChronoUnit.DAYS.between(startOfWeek, today) + 1;

        long completedThisWeek = 0;

        for (Habit habit : habits) {

            List<HabitLog> logs = habitLogRepository.findByHabitId(habit.getId());

            for (HabitLog log : logs) {

                if (log.getCompleted()
                        && !log.getLogDate().isBefore(startOfWeek)
                        && !log.getLogDate().isAfter(today)) {

                    completedThisWeek++;
                }
            }
        }

        int totalWeeklyTarget = habits.stream()
                .mapToInt(Habit::getTargetDaysPerWeek)
                .sum();

        double expectedTillToday =
                totalWeeklyTarget * (daysPassed / 7.0);

        if (expectedTillToday == 0) return 0;

        int score = (int) ((completedThisWeek * 100) / expectedTillToday);

        return Math.min(score, 100);
    }
}