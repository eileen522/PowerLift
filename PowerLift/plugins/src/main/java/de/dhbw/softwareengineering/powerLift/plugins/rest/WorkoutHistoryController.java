package de.dhbw.softwareengineering.powerLift.plugins.rest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.dhbw.softwareengineering.powerLift.application.services.workoutHistory.WorkoutHistoryService;
import de.dhbw.softwareengineering.powerLift.domain.entities.Workout;
import de.dhbw.softwareengineering.powerLift.domain.entities.WorkoutHistory;

@RestController
@RequestMapping("/workoutHistorys")
public class WorkoutHistoryController {
	
	@Autowired 
	private WorkoutHistoryService workoutHistoryService;
	
	@PostMapping
    public ResponseEntity<String> addWorkoutToHistory(@RequestParam Workout workout, @RequestParam String date) {
        LocalDate workoutDate = LocalDate.parse(date);
        WorkoutHistory workoutHistory = new WorkoutHistory(workout, workout.getUser(), workoutDate);
        workoutHistoryService.createWorkoutHistory(workoutHistory);
        return ResponseEntity.status(HttpStatus.CREATED).body("Workout added to history");
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkoutHistory> getWorkoutHistoryById(@PathVariable UUID id) {
        Optional<WorkoutHistory> workoutHistory = workoutHistoryService.findByWorkoutId(id);
        return workoutHistory.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/workout/{workoutId}")
    public ResponseEntity<WorkoutHistory> getWorkoutHistoryByWorkoutId(@PathVariable UUID workoutId) {
        Optional<WorkoutHistory> workoutHistories = workoutHistoryService.findByWorkoutId(workoutId);
        return workoutHistories.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<Optional<WorkoutHistory>> getWorkoutHistoryByDate(@PathVariable String date) {
        LocalDate workoutDate = LocalDate.parse(date);
        Optional<WorkoutHistory> workoutHistories = workoutHistoryService.findByDate(workoutDate);
        return ResponseEntity.ok(workoutHistories);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<WorkoutHistory>> getWorkoutHistoryByUserId(@PathVariable UUID userId) {
        List<WorkoutHistory> workoutHistories = workoutHistoryService.findByUserId(userId);
        return ResponseEntity.ok(workoutHistories);
    }

    @GetMapping
    public ResponseEntity<List<WorkoutHistory>> getAllWorkoutHistories() {
        List<WorkoutHistory> workoutHistories = workoutHistoryService.getAllWorkoutHistorys();
        return ResponseEntity.ok(workoutHistories);
    }

}
