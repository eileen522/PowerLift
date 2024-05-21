package de.dhbw.softwareengineering.powerLift.plugins.rest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.dhbw.softwareengineering.powerLift.application.services.trainingSet.TrainingSetService;
import de.dhbw.softwareengineering.powerLift.domain.entities.TrainingSet;

@RestController
@RequestMapping("/trainingSets")
public class TrainingSetController {
	
	@Autowired 
	private TrainingSetService trainingSetService;
	
	@GetMapping("/{id}")
    public ResponseEntity<TrainingSet> getTrainingSetById(@PathVariable UUID id) {
    	Optional<TrainingSet> trainingSet = trainingSetService.getTrainingSetById(id);
    	return trainingSet.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<TrainingSet>> getAllTrainingSets() {
    	List<TrainingSet> trainingSets = trainingSetService.getAllTrainingSets();
    	return ResponseEntity.ok(trainingSets);
    }
    
    @PostMapping
    public ResponseEntity<String> createTrainingSet(@RequestBody TrainingSet trainingSet) {
		trainingSetService.createTrainingSet(trainingSet);
		return ResponseEntity.status(HttpStatus.CREATED).body("TrainingSet created successfully");
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<String> updateTrainingSet(@PathVariable UUID id, @RequestBody TrainingSet updatedTrainingSet) {
    	
    	try {
    		trainingSetService.updateTrainingSet(id, updatedTrainingSet.getExercise(), updatedTrainingSet.getSets(), updatedTrainingSet.getReps(), updatedTrainingSet.getRpe());
            return ResponseEntity.ok("Exercise updated successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExercise(@PathVariable UUID id) {
    	trainingSetService.deleteTrainingSet(id);
    	return ResponseEntity.noContent().build();
    }

}
