package de.dhbw.softwareengineering.powerLift.application.services.trainingSet;

import java.util.UUID;

import org.springframework.context.ApplicationEvent;

public class TrainingSetDeletedEvent extends ApplicationEvent {
	
	private static final long serialVersionUID = 1L;	
    private final UUID trainingSetId;

    public TrainingSetDeletedEvent(Object source, UUID trainingSetId) {
        super(source);
        this.trainingSetId = trainingSetId;
    }

    public UUID getTrainingSetId() {
        return trainingSetId;
    }
}