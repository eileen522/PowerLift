package de.dhbw.softwareengineering.powerLift.plugins.persistence;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import de.dhbw.softwareengineering.powerLift.domain.entities.TrainingSet;

public interface SpringDataForTrainingSets extends JpaRepository<TrainingSet, UUID> {

}
