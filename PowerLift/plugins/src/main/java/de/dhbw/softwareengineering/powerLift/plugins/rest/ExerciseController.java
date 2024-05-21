package de.dhbw.softwareengineering.powerLift.plugins.rest;

import de.dhbw.softwareengineering.powerLift.application.services.exercise.ExerciseAlreadyExistsException;
import de.dhbw.softwareengineering.powerLift.application.services.exercise.ExerciseService;
import de.dhbw.softwareengineering.powerLift.domain.entities.Exercise;

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

@RestController
@RequestMapping("/exercises")
public class ExerciseController {
    
	@Autowired
    private ExerciseService exerciseService;

    @GetMapping("/{id}")
    public ResponseEntity<Exercise> getExerciseById(@PathVariable UUID id) {
    	Optional<Exercise> exercise = exerciseService.getExerciseById(id);
    	return exercise.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/byName/{name}")
    public ResponseEntity<Exercise> getExerciseByName(@PathVariable String name) {
    	Optional<Exercise> exercise = exerciseService.getExerciseByName(name);
    	return exercise.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Exercise>> getAllExercises() {
    	List<Exercise> exercises = exerciseService.getAllExercises();
    	return ResponseEntity.ok(exercises);
    }
    
    @PostMapping
    public ResponseEntity<String> createExercise(@RequestBody Exercise exercise) {
    	try {
            exerciseService.createExercise(exercise);
            return ResponseEntity.status(HttpStatus.CREATED).body("Exercise created successfully");
        } catch (ExerciseAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<String> updateExercise(@PathVariable UUID id, @RequestBody Exercise updatedExercise) {
    	
    	try {
    		exerciseService.updateExercise(id, updatedExercise.getName(), updatedExercise.getDescription(), updatedExercise.getCategory());
            return ResponseEntity.ok("Exercise updated successfully");
        } catch (ExerciseAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExercise(@PathVariable UUID id) {
        exerciseService.deleteExercise(id);
        return ResponseEntity.noContent().build();
    }
    
}