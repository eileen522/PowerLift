package de.dhbw.softwareengineering.powerLift.application.services.user;

import org.springframework.context.ApplicationEvent;

import java.util.UUID;


public class UserDeletedEvent extends ApplicationEvent {
	
	private static final long serialVersionUID = 1L;	
    private final UUID userId;

    public UserDeletedEvent(Object source, UUID userId) {
        super(source);
        this.userId = userId;
    }

    public UUID getUserId() {
        return userId;
    }
}