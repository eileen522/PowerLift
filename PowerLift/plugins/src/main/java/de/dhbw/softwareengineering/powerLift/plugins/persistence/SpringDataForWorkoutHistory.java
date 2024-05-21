package de.dhbw.softwareengineering.powerLift.plugins.persistence;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import de.dhbw.softwareengineering.powerLift.domain.entities.WorkoutHistory;

public interface SpringDataForWorkoutHistory extends JpaRepository<WorkoutHistory, UUID> {

	Optional<WorkoutHistory> findByWorkoutId(UUID workoutId);
    Optional<WorkoutHistory> findByDate(LocalDate date);
    List<WorkoutHistory> findByUserId(UUID userId);
}
