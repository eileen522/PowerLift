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

import de.dhbw.softwareengineering.powerLift.application.services.workoutPlan.WorkoutPlanAlreadyExistsException;
import de.dhbw.softwareengineering.powerLift.application.services.workoutPlan.WorkoutPlanService;
import de.dhbw.softwareengineering.powerLift.domain.entities.WorkoutPlan;

@RestController
@RequestMapping("/workoutPlans")
public class WorkoutPlanController {

	@Autowired
	private WorkoutPlanService workoutPlanService;
	
	@GetMapping("/{id}")
	public ResponseEntity<WorkoutPlan> getWorkoutPlanById(@PathVariable UUID id) {
		Optional<WorkoutPlan> workoutPlan = workoutPlanService.getWorkoutPlanById(id);
		return workoutPlan.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<WorkoutPlan> getWorkoutPlanByName(@PathVariable String name) {
	    Optional<WorkoutPlan> workoutPlan = workoutPlanService.getWorkoutPlanByName(name);
	    return workoutPlan.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@GetMapping("/userId/{userId}")
    public ResponseEntity<List<WorkoutPlan>> getWorkoutPlanByUserId(@PathVariable UUID userId) {
		List<WorkoutPlan> workoutPlans = workoutPlanService.findByUserId(userId);
		return ResponseEntity.ok(workoutPlans);
	}
		
	@GetMapping
    public ResponseEntity<List<WorkoutPlan>> getAllWorkoutPlans() {
		List<WorkoutPlan> workoutPlans = workoutPlanService.getAllWorkoutPlans();
		return ResponseEntity.ok(workoutPlans);
	}
	
    @PostMapping
    public ResponseEntity<String> createWorkoutPlan(@RequestBody WorkoutPlan workoutPlan) throws WorkoutPlanAlreadyExistsException {
    	try {
            workoutPlanService.createWorkoutPlan(workoutPlan);
            return ResponseEntity.status(HttpStatus.CREATED).body("WorkoutPlan created successfully");
        } catch (WorkoutPlanAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<String> updateWorkoutPlan(@PathVariable UUID id, @RequestBody WorkoutPlan updatedWorkoutPlan) throws WorkoutPlanAlreadyExistsException {
    	
    	try {
    		workoutPlanService.updateWorkoutPlan(id, updatedWorkoutPlan.getName(), updatedWorkoutPlan.getDescription(),  updatedWorkoutPlan.getUser(), updatedWorkoutPlan.getWorkouts());
            return ResponseEntity.ok("WorkoutPlan updated successfully");
        } catch (WorkoutPlanAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkoutPlan(@PathVariable UUID id) {
    	workoutPlanService.deleteWorkoutPlan(id);
        return ResponseEntity.noContent().build();
    }
	
}