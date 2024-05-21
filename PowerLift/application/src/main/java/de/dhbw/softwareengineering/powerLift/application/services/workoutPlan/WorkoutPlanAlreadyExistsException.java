package de.dhbw.softwareengineering.powerLift.application.services.workoutPlan;

public class WorkoutPlanAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public WorkoutPlanAlreadyExistsException(String message) {
        super(message);
    }
}
