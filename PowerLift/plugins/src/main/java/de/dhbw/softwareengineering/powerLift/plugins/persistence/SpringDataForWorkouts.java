package de.dhbw.softwareengineering.powerLift.plugins.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import de.dhbw.softwareengineering.powerLift.domain.entities.Workout;

public interface SpringDataForWorkouts extends JpaRepository<Workout, UUID> {
	
	Optional<Workout> findByName(String name);
	List<Workout> findByUser_Id(UUID userId);

}
