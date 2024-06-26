package de.dhbw.softwareengineering.powerLift.domain.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import de.dhbw.softwareengineering.powerLift.domain.entities.WorkoutHistory;

public interface WorkoutHistoryRepository {
	
	Optional<WorkoutHistory> findByWorkoutHistoryId(UUID workoutHistoryId);
    Optional<WorkoutHistory> findByDate(LocalDate date);
    List<WorkoutHistory> findByUserId(UUID userId);
    List<WorkoutHistory> getAllWorkoutHistorys();
    WorkoutHistory createWorkoutHistory(WorkoutHistory workoutHistory);

}
