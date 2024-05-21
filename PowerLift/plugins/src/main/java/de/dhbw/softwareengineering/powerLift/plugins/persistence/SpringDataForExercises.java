package de.dhbw.softwareengineering.powerLift.plugins.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import de.dhbw.softwareengineering.powerLift.domain.entities.Exercise;

public interface SpringDataForExercises extends JpaRepository<Exercise, UUID> {

    Optional<Exercise> findByName(String name);
	
}