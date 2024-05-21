package de.dhbw.softwareengineering.powerLift.domain.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import de.dhbw.softwareengineering.powerLift.domain.entities.User;

public interface UserRepository {
	
	Optional<User> getUserById(UUID id);
    Optional<User> getUserByEmail(String email);
    Optional<User> getUserByUsername(String username);
    List<User> getAllUsers();
    void createUser(User user);
	void updateUser(User user);  
    void deleteUser(UUID id);
}
