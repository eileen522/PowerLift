package de.dhbw.softwareengineering.powerLift.application.services.exercise;

public class ExerciseAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public ExerciseAlreadyExistsException(String message) {
		super(message);
	}
	
}
