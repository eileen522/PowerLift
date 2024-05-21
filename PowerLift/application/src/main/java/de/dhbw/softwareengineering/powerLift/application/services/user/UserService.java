package de.dhbw.softwareengineering.powerLift.application.services.user;

import de.dhbw.softwareengineering.powerLift.domain.entities.User;
import de.dhbw.softwareengineering.powerLift.domain.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
    private final UserRepository userRepository;
    private final ApplicationEventPublisher eventPublisher;
	
	@Autowired
    public UserService(UserRepository userRepository, ApplicationEventPublisher eventPublisher) {
        this.userRepository = userRepository;
        this.eventPublisher = eventPublisher;
    }
	
	public Optional<User> getUserById(UUID id) {
        return userRepository.getUserById(id);
    }

	public Optional<User> getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }
	
	public Optional<User> getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }
	
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public void create(User user) throws UserAlreadyExistsException {
        validateUserUniqueness(user.getUsername(), user.getEmail(), null);

        User newUser = new User.Builder()
                .withUsername(user.getUsername())
                .withEmail(user.getEmail())
                .withPassword(user.getPassword())
                .build();

        userRepository.createUser(newUser);
    }

    public void updateUser(UUID id, String newUsername, String newEmail, String newPassword) throws UserAlreadyExistsException {
        Optional<User> existingUser = userRepository.getUserById(id);

        if (existingUser.isPresent()) {
            validateUserUniqueness(newUsername, newEmail, id);

            User updatedUser = new User.Builder()
                    .withUsername(newUsername)
                    .withEmail(newEmail)
                    .withPassword(newPassword)
                    .build();

            userRepository.updateUser(updatedUser);
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }
    
    private void validateUserUniqueness(String username, String email, UUID userId) throws UserAlreadyExistsException {
        if (userRepository.getUserByUsername(username).isPresent() &&
                (userId == null || !userRepository.getUserByUsername(username).get().getId().equals(userId))) {
            throw new UserAlreadyExistsException("Username already exists");
        }

        if (userRepository.getUserByEmail(email).isPresent() &&
                (userId == null || !userRepository.getUserByEmail(email).get().getId().equals(userId))) {
            throw new UserAlreadyExistsException("Email already exists");
        }
    }

    public void deleteUser(UUID id) {
        userRepository.deleteUser(id);
        eventPublisher.publishEvent(new UserDeletedEvent(this, id));
    }
}
