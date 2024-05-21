package de.dhbw.softwareengineering.powerLift.application.services.exercise;

import org.springframework.context.ApplicationEvent;

import java.util.UUID;

public class ExerciseDeletedEvent extends ApplicationEvent {
	
	private static final long serialVersionUID = 1L;	
    private final UUID exerciseId;

    public ExerciseDeletedEvent(Object source, UUID exerciseId) {
        super(source);
        this.exerciseId = exerciseId;
    }

    public UUID getExerciseId() {
        return exerciseId;
    }
}