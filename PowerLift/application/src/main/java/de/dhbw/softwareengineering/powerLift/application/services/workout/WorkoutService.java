package de.dhbw.softwareengineering.powerLift.application.services.workout;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import de.dhbw.softwareengineering.powerLift.domain.entities.TrainingSet;
import de.dhbw.softwareengineering.powerLift.domain.entities.User;
import de.dhbw.softwareengineering.powerLift.domain.entities.Workout;
import de.dhbw.softwareengineering.powerLift.domain.repositories.WorkoutRepository;

@Service
public class WorkoutService {

	private final WorkoutRepository workoutRepository;
	private final ApplicationEventPublisher eventPublisher;
		
	@Autowired
	public WorkoutService(WorkoutRepository workoutRepository, ApplicationEventPublisher eventPublisher) {
		this.workoutRepository = workoutRepository;
		this.eventPublisher = eventPublisher;
	}
	
	public Optional<Workout> getWorkoutById(UUID id) {
        return workoutRepository.getWorkoutById(id);
    }
	
	public Optional<Workout> getWorkoutByName(String name) {
		return workoutRepository.getWorkoutByName(name);
	}
	
    public List<Workout> findByUserId(UUID userId) {
    	return workoutRepository.findByUserId(userId);
    }
    
    public List<Workout> getAllWorkouts() {
    	return workoutRepository.getAllWorkouts();
    }
    
    public void createWorkout(Workout workout) throws WorkoutAlreadyExistsException {
    	
    	if (workoutRepository.getWorkoutByName(workout.getName()).isPresent()) {
    		throw new WorkoutAlreadyExistsException("A Workout with this name already exists");
    	}
    	
    	Workout newWorkout = new Workout(workout.getName(), workout.getUser(), workout.getTrainingSets());
    	
    	workoutRepository.createWorkout(newWorkout);
    }
    
    public void updateWorkout(UUID id, String name, User user, List<TrainingSet> trainingSets) throws WorkoutAlreadyExistsException {
    	Optional<Workout> existingWorkout = workoutRepository.getWorkoutById(id);

        if (existingWorkout.isPresent()) {
            Optional<Workout> workoutWithSameName = workoutRepository.getWorkoutByName(name);
            
            if (workoutWithSameName.isPresent() && !workoutWithSameName.get().getId().equals(id)) {
                throw new WorkoutAlreadyExistsException("A Workout with this name already exists");
            }
            
            Workout updatedWorkout = existingWorkout.get();
            updatedWorkout.setName(name);
            updatedWorkout.setUser(user);
            updatedWorkout.setTrainingSets(trainingSets);
            workoutRepository.updateWorkout(updatedWorkout);
        } else {
            throw new IllegalArgumentException("Workout not found");
        }
    }
    
    public void deleteWorkout(UUID id) {
    	workoutRepository.deleteWorkout(id);
    	eventPublisher.publishEvent(new WorkoutDeletedEvent(this, id));
    }
		
}
