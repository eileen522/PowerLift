package de.dhbw.softwareengineering.powerLift.plugins.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import de.dhbw.softwareengineering.powerLift.domain.entities.TrainingSet;
import de.dhbw.softwareengineering.powerLift.domain.repositories.TrainingSetRepository;

@Repository
public class TrainingSets implements TrainingSetRepository {
	
	@Autowired
	private SpringDataForTrainingSets springDataForTrainingSets;

	@Override
	public Optional<TrainingSet> getTrainingSetById(UUID id) {
		return springDataForTrainingSets.findById(id);
	}

	@Override
	public List<TrainingSet> getAllTrainingSets() {
		return springDataForTrainingSets.findAll();
	}

	@Override
	public void createTrainingSet(TrainingSet trainingSet) {
		springDataForTrainingSets.save(trainingSet);
	}

	@Override
	public void updateTrainingSet(TrainingSet trainingSet) {
		springDataForTrainingSets.save(trainingSet);
	}

	@Override
	public void deleteTrainingSet(UUID id) {
		springDataForTrainingSets.deleteById(id);
	}
	

}
