package de.dhbw.softwareengineering.powerLift.domain.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import de.dhbw.softwareengineering.powerLift.domain.entities.Exercise;

public interface ExerciseRepository {
	
	Optional<Exercise> getExerciseById(UUID id);
    Optional<Exercise> getExerciseByName(String name);
    List<Exercise> getAllExercises();
    void createExercise(Exercise exercise);
	void updateExercise(Exercise exercise);
    void deleteExercise(UUID id);
}
