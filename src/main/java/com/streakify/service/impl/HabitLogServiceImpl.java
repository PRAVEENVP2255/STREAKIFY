package com.streakify.service.impl;

import com.streakify.dto.HabitLogDTO;
import com.streakify.dto.HabitLogRequestDTO;
import com.streakify.dto.HabitLogResponseDTO;
import com.streakify.dto.StreakResponseDTO;
import com.streakify.entity.Habit;
import com.streakify.entity.HabitLog;
import com.streakify.exception.DuplicateLogException;
import com.streakify.exception.FutureDateException;
import com.streakify.exception.HabitNotFoundException;
import com.streakify.repository.HabitLogRepository;
import com.streakify.repository.HabitRepository;
import com.streakify.service.HabitLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HabitLogServiceImpl implements HabitLogService {

    private final HabitLogRepository habitLogRepository;
    private final HabitRepository habitRepository;

    @Override
    public HabitLogResponseDTO createLog(Long habitId, HabitLogRequestDTO request) {

        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() -> new HabitNotFoundException("Habit not found"));

        if (request.getLogDate().isAfter(LocalDate.now())) {
            throw new FutureDateException("Cannot log future dates");
        }

        habitLogRepository.findByHabitIdAndLogDate(habitId, request.getLogDate())
                .ifPresent(log -> {
                    throw new DuplicateLogException("Log already exists for this date");
                });

        HabitLog habitLog = HabitLog.builder()
                .habit(habit)
                .logDate(request.getLogDate())
                .completed(request.getCompleted())
                .build();

        HabitLog savedLog = habitLogRepository.save(habitLog);

        String weeklyStatus = calculateWeeklyStatus(habit);

        return HabitLogResponseDTO.builder()
                .id(savedLog.getId())
                .habitId(savedLog.getHabit().getId())
                .logDate(savedLog.getLogDate())
                .completed(savedLog.getCompleted())
                .weeklyStatus(weeklyStatus)
                .build();
    }

    @Override
    public HabitLogResponseDTO updateLog(Long habitId, LocalDate date, HabitLogRequestDTO request) {

        HabitLog habitLog = habitLogRepository.findByHabitIdAndLogDate(habitId, date)
                .orElseThrow(() -> new RuntimeException("Log not found for this date"));

        habitLog.setCompleted(request.getCompleted());

        HabitLog updatedLog = habitLogRepository.save(habitLog);

        String weeklyStatus = calculateWeeklyStatus(updatedLog.getHabit());

        return HabitLogResponseDTO.builder()
                .id(updatedLog.getId())
                .habitId(updatedLog.getHabit().getId())
                .logDate(updatedLog.getLogDate())
                .completed(updatedLog.getCompleted())
                .weeklyStatus(weeklyStatus)
                .build();
    }

    @Override
    public List<HabitLogDTO> getLogsByHabit(Long habitId) {

        return habitLogRepository.findByHabitId(habitId)
                .stream()
                .map(log -> HabitLogDTO.builder()
                        .id(log.getId())
                        .habitId(log.getHabit().getId())
                        .logDate(log.getLogDate())
                        .completed(log.getCompleted())
                        .build())
                .toList();
    }

    private HabitLogResponseDTO mapToDTO(HabitLog habitLog) {

        return HabitLogResponseDTO.builder()
                .id(habitLog.getId())
                .habitId(habitLog.getHabit().getId())
                .logDate(habitLog.getLogDate())
                .completed(habitLog.getCompleted())
                .build();
    }

    @Override
    public StreakResponseDTO calculateStreak(Long habitId) {

        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() -> new HabitNotFoundException("Habit not found"));

        List<HabitLog> logs = habitLogRepository.findByHabitId(habitId)
                .stream()
                .sorted((a, b) -> a.getLogDate().compareTo(b.getLogDate()))
                .toList();

        int longestStreak = 0;
        int tempStreak = 0;

        for (HabitLog log : logs) {
            if (Boolean.TRUE.equals(log.getCompleted())) {
                tempStreak++;
                longestStreak = Math.max(longestStreak, tempStreak);
            } else {
                tempStreak = 0;
            }
        }

        LocalDate today = LocalDate.now();
        LocalDate datePointer = today;

        HabitLog todayLog = habitLogRepository
                .findByHabitIdAndLogDate(habitId, today)
                .orElse(null);

        if (todayLog == null) {
            datePointer = today.minusDays(1);
        }

        int currentStreak = 0;

        while (true) {
            HabitLog log = habitLogRepository
                    .findByHabitIdAndLogDate(habitId, datePointer)
                    .orElse(null);

            if (log != null && Boolean.TRUE.equals(log.getCompleted())) {
                currentStreak++;
                datePointer = datePointer.minusDays(1);
            } else {
                break;
            }
        }

        return StreakResponseDTO.builder()
                .currentStreak(currentStreak)
                .longestStreak(longestStreak)
                .build();
    }

    private LocalDate[] getCurrentWeekRange() {

        LocalDate today = LocalDate.now();

        LocalDate sunday = today.minusDays(today.getDayOfWeek().getValue() % 7);
        LocalDate saturday = sunday.plusDays(6);

        return new LocalDate[]{sunday, saturday};
    }

    private int countCompletedThisWeek(Habit habit) {

        LocalDate[] range = getCurrentWeekRange();
        LocalDate sunday = range[0];
        LocalDate saturday = range[1];

        return (int) habitLogRepository
                .countByHabitIdAndCompletedTrueAndLogDateBetween(
                        habit.getId(),
                        sunday,
                        saturday
                );
    }

    private String calculateWeeklyStatus(Habit habit) {

        int completedThisWeek = countCompletedThisWeek(habit);
        int target = habit.getTargetDaysPerWeek();

        LocalDate[] range = getCurrentWeekRange();
        LocalDate today = LocalDate.now();
        LocalDate saturday = range[1];

        if (completedThisWeek >= target) {

            if (!today.isAfter(saturday)) {
                return "Target achieved, Keep going ";
            }

            return "Target achieved ";
        }

        if (!today.isAfter(saturday)) {
            return "In progress ";
        }

        return "Target not achieved this week";
    }

}