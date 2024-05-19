package de.dhbw.softwareengineering.powerLift.plugins.rest;

import de.dhbw.softwareengineering.powerLift.application.services.ExerciseEntryService;
import de.dhbw.softwareengineering.powerLift.domain.entities.ExerciseEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exercise-entries")
public class ExerciseEntryController {
    
	/*@Autowired
    private ExerciseEntryService exerciseEntryService;

    @GetMapping("/{id}")
    public ResponseEntity<ExerciseEntry> getExerciseEntryById(@PathVariable String id) {
        ExerciseEntry exerciseEntry = exerciseEntryService.findById(id);
        if (exerciseEntry != null) {
            return ResponseEntity.ok(exerciseEntry);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<ExerciseEntry> createExerciseEntry(@RequestBody ExerciseEntry exerciseEntry) {
        exerciseEntryService.save(exerciseEntry);
        return ResponseEntity.status(HttpStatus.CREATED).body(exerciseEntry);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExerciseEntryById(@PathVariable String id) {
        exerciseEntryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }*/

    // Other CRUD operations can be added here
}
