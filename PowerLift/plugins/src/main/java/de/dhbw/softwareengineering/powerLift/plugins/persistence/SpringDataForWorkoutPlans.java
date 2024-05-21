package de.dhbw.softwareengineering.powerLift.plugins.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import de.dhbw.softwareengineering.powerLift.domain.entities.WorkoutPlan;

public interface SpringDataForWorkoutPlans extends JpaRepository<WorkoutPlan, UUID> {
	
	Optional<WorkoutPlan> findByName(String name);
	List<WorkoutPlan> findByUserId(UUID userId);

}
