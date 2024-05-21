package de.dhbw.softwareengineering.powerLift.application.services.trainingSet;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import de.dhbw.softwareengineering.powerLift.domain.entities.Exercise;
import de.dhbw.softwareengineering.powerLift.domain.entities.TrainingSet;
import de.dhbw.softwareengineering.powerLift.domain.repositories.TrainingSetRepository;

@Service
public class TrainingSetService {
	
	private final TrainingSetRepository trainingSetRepository;
	private final ApplicationEventPublisher eventPublisher;
	
	@Autowired
    public TrainingSetService(TrainingSetRepository trainingSetRepository, ApplicationEventPublisher eventPublisher) {
        this.trainingSetRepository = trainingSetRepository;
        this.eventPublisher = eventPublisher;
    }
	
	public Optional<TrainingSet> getTrainingSetById(UUID id) {
		return trainingSetRepository.getTrainingSetById(id);
	}

    public List<TrainingSet> getAllTrainingSets() {
        return trainingSetRepository.getAllTrainingSets();
    }
    
    public void createTrainingSet(TrainingSet trainingSet) {
    	TrainingSet newTrainingSet = new TrainingSet(trainingSet.getExercise(), trainingSet.getSets(), trainingSet.getReps(), trainingSet.getRpe());
    	
    	trainingSetRepository.createTrainingSet(newTrainingSet);
    }
    
    public void updateTrainingSet(UUID id, Exercise exercise, Integer sets, Integer reps, double rpe) {
    	 Optional<TrainingSet> existingTrainingSet = trainingSetRepository.getTrainingSetById(id);
         if (existingTrainingSet.isPresent()) {
             TrainingSet updatedTrainingSet = existingTrainingSet.get();
             updatedTrainingSet.setExercise(exercise);
             updatedTrainingSet.setSets(sets);
             updatedTrainingSet.setReps(reps);
             updatedTrainingSet.setRpe(rpe);
             trainingSetRepository.updateTrainingSet(updatedTrainingSet);
         } else {
             throw new IllegalArgumentException("TrainingSet not found");
         }
    	
    }
    
    public void deleteTrainingSet(UUID id) {
    	trainingSetRepository.deleteTrainingSet(id);
        eventPublisher.publishEvent(new TrainingSetDeletedEvent(this, id));
    }
	
}
