package de.dhbw.softwareengineering.powerLift.application.services.workout;

public class WorkoutAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public WorkoutAlreadyExistsException(String message) {
        super(message);
    }

}
