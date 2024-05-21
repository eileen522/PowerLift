package de.dhbw.softwareengineering.powerLift.application.services.workoutPlan;

import java.util.UUID;

import org.springframework.context.ApplicationEvent;

public class WorkoutPlanDeletedEvent extends ApplicationEvent {
	
	private static final long serialVersionUID = 1L;	
    private final UUID workoutPlanId;

    public WorkoutPlanDeletedEvent(Object source, UUID workoutPlanId) {
        super(source);
        this.workoutPlanId = workoutPlanId;
    }

    public UUID getWorkoutPlanId() {
        return workoutPlanId;
    }
}
