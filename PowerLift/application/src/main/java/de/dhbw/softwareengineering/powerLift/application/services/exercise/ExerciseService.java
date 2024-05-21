package de.dhbw.softwareengineering.powerLift.application.services.exercise;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import de.dhbw.softwareengineering.powerLift.domain.entities.Exercise;
import de.dhbw.softwareengineering.powerLift.domain.repositories.ExerciseRepository;

@Service
public class ExerciseService {
	
	private final ExerciseRepository exerciseRepository;
    private final ApplicationEventPublisher eventPublisher;
	
	@Autowired
    public ExerciseService(ExerciseRepository exerciseRepository, ApplicationEventPublisher eventPublisher) {
        this.exerciseRepository = exerciseRepository;
        this.eventPublisher = eventPublisher;
    }
	
	public Optional<Exercise> getExerciseById(UUID id) {
        return exerciseRepository.getExerciseById(id);
    }
	
	public Optional<Exercise> getExerciseByName(String name) {
        return exerciseRepository.getExerciseByName(name);
    }
	
    public List<Exercise> getAllExercises() {
        return exerciseRepository.getAllExercises();
    }

    public void createExercise(Exercise exercise) throws ExerciseAlreadyExistsException {
        
    	if (exerciseRepository.getExerciseByName(exercise.getName()).isPresent()) {
    		throw new ExerciseAlreadyExistsException("Exercise already exists");
    	}
    	
        Exercise newExercise = new Exercise(exercise.getName(), exercise.getDescription(), exercise.getCategory());

        exerciseRepository.createExercise(newExercise);
    }
    
    public void updateExercise(UUID id, String newName, String newDescription, String newCategory) throws ExerciseAlreadyExistsException {
        Optional<Exercise> existingExercise = exerciseRepository.getExerciseById(id);
        		

        if (existingExercise.isPresent()) {
        	Exercise exercise = existingExercise.get();

            if (!exercise.getName().equals(newName) && exerciseRepository.getExerciseByName(newName).isPresent()) {
                throw new ExerciseAlreadyExistsException("Exercise already exists");
            }

            exercise.setName(newName);
            exercise.setDescription(newDescription);
            exercise.setCategory(newCategory);

            exerciseRepository.updateExercise(exercise);
        } else {
            throw new IllegalArgumentException("Exercise not found");
        }
    }

    public void deleteExercise(UUID id) {
    	exerciseRepository.deleteExercise(id);
        eventPublisher.publishEvent(new ExerciseDeletedEvent(this, id));
    }

}
