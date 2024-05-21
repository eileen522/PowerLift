package de.dhbw.softwareengineering.powerLift.domain.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import de.dhbw.softwareengineering.powerLift.domain.entities.WorkoutPlan;

public interface WorkoutPlanRepository {
	
	Optional<WorkoutPlan> getWorkoutPlanById(UUID id);
	Optional<WorkoutPlan> getWorkoutPlanByName(String name);
    List<WorkoutPlan> findByUserId(UUID userId);
    List<WorkoutPlan> getAllWorkoutPlans();
    void createWorkoutPlan(WorkoutPlan workoutPlan);
    void updateWorkoutPlan(WorkoutPlan workoutPlan);
    void deleteWorkoutPlan(UUID id);

}
