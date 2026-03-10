package com.streakify.repository;

import com.streakify.entity.HabitLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HabitLogRepository extends JpaRepository<HabitLog, Long> {

    List<HabitLog> findByHabitId(Long habitId);

    Optional<HabitLog> findByHabitIdAndLogDate(Long habitId, LocalDate logDate);

    long countByHabitIdAndCompletedTrueAndLogDateBetween(
            Long habitId,
            LocalDate startDate,
            LocalDate endDate
    );

}