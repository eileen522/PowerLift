package de.dhbw.softwareengineering.powerLift.application.services.workoutPlan;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import de.dhbw.softwareengineering.powerLift.domain.entities.Workout;
import de.dhbw.softwareengineering.powerLift.domain.entities.WorkoutPlan;
import de.dhbw.softwareengineering.powerLift.domain.repositories.WorkoutPlanRepository;

@Service
public class WorkoutPlanService {
	
	private final WorkoutPlanRepository workoutPlanRepository;
	private final ApplicationEventPublisher eventPublisher;
		
	@Autowired
	public WorkoutPlanService(WorkoutPlanRepository workoutPlanRepository, ApplicationEventPublisher eventPublisher) {
		this.workoutPlanRepository = workoutPlanRepository;
		this.eventPublisher = eventPublisher;
	}
	
	
	public Optional<WorkoutPlan> getWorkoutPlanById(UUID id) {
		return workoutPlanRepository.getWorkoutPlanById(id);
	}
	public Optional<WorkoutPlan> getWorkoutPlanByName(String name) {
		return workoutPlanRepository.getWorkoutPlanByName(name);
	}
	public List<WorkoutPlan> findByUserId(UUID userId) {
		return workoutPlanRepository.findByUserId(userId);
	}
	public List<WorkoutPlan> getAllWorkoutPlans() {
		return workoutPlanRepository.getAllWorkoutPlans();
	}
	public void createWorkoutPlan(WorkoutPlan workoutPlan) throws WorkoutPlanAlreadyExistsException {
		
		if (workoutPlanRepository.getWorkoutPlanByName(workoutPlan.getName()).isPresent()) {
    		throw new WorkoutPlanAlreadyExistsException("A Workout with this name already exists");
    	}
		
		WorkoutPlan newWorkoutPlan = new WorkoutPlan(workoutPlan.getName(), workoutPlan.getDescription(), workoutPlan.getWorkouts());
    	
    	workoutPlanRepository.createWorkoutPlan(newWorkoutPlan);
		
	}
	public void updateWorkoutPlan(UUID id, String name, String description, List<Workout> workouts) throws WorkoutPlanAlreadyExistsException {
		
		Optional<WorkoutPlan> existingWorkoutPlan = workoutPlanRepository.getWorkoutPlanById(id);

        if (existingWorkoutPlan.isPresent()) {
            Optional<WorkoutPlan> workoutWithSameName = workoutPlanRepository.getWorkoutPlanByName(name);
            
            if (workoutWithSameName.isPresent() && !workoutWithSameName.get().getId().equals(id)) {
                throw new WorkoutPlanAlreadyExistsException("A WorkoutPlan with this name already exists");
            }
            
            WorkoutPlan updatedWorkoutPlan = existingWorkoutPlan.get();
            updatedWorkoutPlan.setName(name);
            updatedWorkoutPlan.setDescription(description);	
            updatedWorkoutPlan.setWorkouts(workouts);
            workoutPlanRepository.updateWorkoutPlan(updatedWorkoutPlan);
        } else {
            throw new IllegalArgumentException("Workout not found");
        }
		
	}
	public void deleteWorkoutPlan(UUID id) {
		workoutPlanRepository.deleteWorkoutPlan(id);
    	eventPublisher.publishEvent(new WorkoutPlanDeletedEvent(this, id));
	}
	

}
