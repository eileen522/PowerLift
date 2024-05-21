package de.dhbw.softwareengineering.powerLift.domain.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import de.dhbw.softwareengineering.powerLift.domain.entities.Workout;

public interface WorkoutRepository {

	Optional<Workout> getWorkoutById(UUID id);
	Optional<Workout> getWorkoutByName(String name);
    List<Workout> findByUserId(UUID userId);
    List<Workout> getAllWorkouts();
    void createWorkout(Workout workout);
    void updateWorkout(Workout workout);
    void deleteWorkout(UUID id);
}
