package de.dhbw.softwareengineering.powerLift.plugins.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import de.dhbw.softwareengineering.powerLift.domain.entities.User;

public interface SpringDataForUsers extends JpaRepository<User, UUID> {
	
	Optional<User> findByEmail(String email);
	Optional<User> findByUsername(String username);

}
