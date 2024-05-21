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

import de.dhbw.softwareengineering.powerLift.application.services.workout.WorkoutAlreadyExistsException;
import de.dhbw.softwareengineering.powerLift.application.services.workout.WorkoutService;
import de.dhbw.softwareengineering.powerLift.domain.entities.Workout;

@RestController
@RequestMapping("/workouts")
public class WorkoutController {
	
	@Autowired 
	private WorkoutService workoutService;

	@GetMapping("/{id}")
	public ResponseEntity<Workout> getWorkoutById(@PathVariable UUID id) {
		Optional<Workout> workout = workoutService.getWorkoutById(id);
		return workout.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<Workout> getWorkoutByName(@PathVariable String name) {
	    Optional<Workout> workout = workoutService.getWorkoutByName(name);
	    return workout.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@GetMapping("/userId/{userId}")
    public ResponseEntity<List<Workout>> getWorkoutByUserId(@PathVariable UUID userId) {
		List<Workout> workouts = workoutService.findByUserId(userId);
		return ResponseEntity.ok(workouts);
	}
		
	@GetMapping
    public ResponseEntity<List<Workout>> getAllWorkouts() {
		List<Workout> workouts = workoutService.getAllWorkouts();
		return ResponseEntity.ok(workouts);
	}
	
    @PostMapping
    public ResponseEntity<String> createWorkout(@RequestBody Workout workout) throws WorkoutAlreadyExistsException {
    	try {
            workoutService.createWorkout(workout);
            return ResponseEntity.status(HttpStatus.CREATED).body("Workout created successfully");
        } catch (WorkoutAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<String> updateWorkout(@PathVariable UUID id, @RequestBody Workout updatedWorkout) throws WorkoutAlreadyExistsException {
    	
    	try {
    		workoutService.updateWorkout(id, updatedWorkout.getName(), updatedWorkout.getUser(), updatedWorkout.getTrainingSets());
            return ResponseEntity.ok("Workout updated successfully");
        } catch (WorkoutAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable UUID id) {
    	workoutService.deleteWorkout(id);
        return ResponseEntity.noContent().build();
    }
	
}
