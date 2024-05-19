package de.dhbw.softwareengineering.powerLift.plugins.rest;

import de.dhbw.softwareengineering.powerLift.application.services.TrainingEntryService;
import de.dhbw.softwareengineering.powerLift.domain.entities.TrainingEntry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/training-entries")
public class TrainingEntryController {
	
	/*@Autowired
    private TrainingEntryService trainingEntryService;

    @GetMapping("/{id}")
    public ResponseEntity<TrainingEntry> getTrainingEntryById(@PathVariable String id) {
        TrainingEntry trainingEntry = trainingEntryService.findById(id);
        if (trainingEntry != null) {
            return ResponseEntity.ok(trainingEntry);
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/

}
