package de.dhbw.softwareengineering.powerLift.config;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class DatabaseInitializer {
	
	@PostConstruct
	public void init() {}

}
