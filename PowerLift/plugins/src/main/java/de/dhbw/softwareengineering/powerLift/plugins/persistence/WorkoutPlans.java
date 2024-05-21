package de.dhbw.softwareengineering.powerLift.plugins.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import de.dhbw.softwareengineering.powerLift.domain.entities.WorkoutPlan;
import de.dhbw.softwareengineering.powerLift.domain.repositories.WorkoutPlanRepository;

@Repository
public class WorkoutPlans implements WorkoutPlanRepository {
	
	@Autowired
    private SpringDataForWorkoutPlans springDataForWorkoutPlans;

	@Override
	public Optional<WorkoutPlan> getWorkoutPlanById(UUID id) {
		return springDataForWorkoutPlans.findById(id);
	}

	@Override
	public Optional<WorkoutPlan> getWorkoutPlanByName(String name) {
		return springDataForWorkoutPlans.findByName(name);
	}

	@Override
	public List<WorkoutPlan> findByUserId(UUID userId) {
		return springDataForWorkoutPlans.findByUserId(userId);
	}

	@Override
	public List<WorkoutPlan> getAllWorkoutPlans() {
		return springDataForWorkoutPlans.findAll();
	}

	@Override
	public void createWorkoutPlan(WorkoutPlan workoutPlan) {
		springDataForWorkoutPlans.save(workoutPlan);
	}

	@Override
	public void updateWorkoutPlan(WorkoutPlan workoutPlan) {
		springDataForWorkoutPlans.save(workoutPlan);
	}

	@Override
	public void deleteWorkoutPlan(UUID id) {
		springDataForWorkoutPlans.deleteById(id);
	}

}
