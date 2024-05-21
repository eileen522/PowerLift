package de.dhbw.softwareengineering.powerLift.domain.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import de.dhbw.softwareengineering.powerLift.domain.entities.TrainingSet;

public interface TrainingSetRepository {
	
	Optional<TrainingSet> getTrainingSetById(UUID id);
    List<TrainingSet> getAllTrainingSets();
    TrainingSet createTrainingSet(TrainingSet trainingSet);
    TrainingSet updateTrainingSet(TrainingSet trainingSet);
    void deleteTrainingSet(UUID id);
    
}
