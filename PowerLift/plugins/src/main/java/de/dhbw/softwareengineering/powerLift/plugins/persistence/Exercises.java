package de.dhbw.softwareengineering.powerLift.plugins.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import de.dhbw.softwareengineering.powerLift.domain.entities.Exercise;
import de.dhbw.softwareengineering.powerLift.domain.repositories.ExerciseRepository;

@Repository
public class Exercises implements ExerciseRepository {
	
	@Autowired
	private SpringDataForExercises springDataForExercises;

	@Override
	public Optional<Exercise> getExerciseById(UUID id) {
		return springDataForExercises.findById(id);
	}

	@Override
	public Optional<Exercise> getExerciseByName(String name) {
		return springDataForExercises.findByName(name);
	}

	@Override
	public List<Exercise> getAllExercises() {
		return springDataForExercises.findAll();
	}

	@Override
	public void createExercise(Exercise exercise) {
		springDataForExercises.save(exercise);
	}

	@Override
	public void updateExercise(Exercise exercise) {
		springDataForExercises.save(exercise);
	}

	@Override
	public void deleteExercise(UUID id) {
		springDataForExercises.deleteById(id);
	}
	
}