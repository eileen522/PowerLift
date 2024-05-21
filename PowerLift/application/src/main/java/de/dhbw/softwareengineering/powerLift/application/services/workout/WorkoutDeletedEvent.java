package de.dhbw.softwareengineering.powerLift.application.services.workout;

import java.util.UUID;

import org.springframework.context.ApplicationEvent;

public class WorkoutDeletedEvent extends ApplicationEvent {
	
	private static final long serialVersionUID = 1L;	
    private final UUID workoutId;

    public WorkoutDeletedEvent(Object source, UUID workoutId) {
        super(source);
        this.workoutId = workoutId;
    }

    public UUID getWorkoutId() {
        return workoutId;
    }
}