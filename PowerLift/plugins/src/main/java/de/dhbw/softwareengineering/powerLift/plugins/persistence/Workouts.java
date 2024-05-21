package de.dhbw.softwareengineering.powerLift.plugins.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import de.dhbw.softwareengineering.powerLift.domain.entities.Workout;
import de.dhbw.softwareengineering.powerLift.domain.repositories.WorkoutRepository;

@Repository
public class Workouts implements WorkoutRepository {
	
	@Autowired
	private SpringDataForWorkouts springDataForWorkouts;

	@Override
	public Optional<Workout> getWorkoutById(UUID id) {
		return springDataForWorkouts.findById(id);
	}
	
	@Override
	public Optional<Workout> getWorkoutByName(String name) {
		return springDataForWorkouts.findByName(name);
	}

	@Override
	public List<Workout> findByUserId(UUID userId) {
		return springDataForWorkouts.findByUser_Id(userId);
	}

	@Override
	public List<Workout> getAllWorkouts() {
		return springDataForWorkouts.findAll();
	}

	@Override
	public void createWorkout(Workout workout) {
		springDataForWorkouts.save(workout);
	}

	@Override
	public void updateWorkout(Workout workout) {
		springDataForWorkouts.save(workout);
	}

	@Override
	public void deleteWorkout(UUID id) {
		springDataForWorkouts.deleteById(id);
	}

}
