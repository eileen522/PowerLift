package de.dhbw.softwareengineering.powerLift.plugins.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import de.dhbw.softwareengineering.powerLift.domain.entities.user.User;
import de.dhbw.softwareengineering.powerLift.domain.repositories.UserRepository;


@Repository
public class Users implements UserRepository {
	
	@Autowired
	private SpringDataForUsers springDataForUsers;

	@Override
	public Optional<User> getUserById(UUID id) {
		return springDataForUsers.findById(id);
	}

	@Override
	public Optional<User> getUserByEmail(String email) {
		return springDataForUsers.findByEmail(email);
	}
	
	@Override
	public Optional<User> getUserByUsername(String username) {
		return springDataForUsers.findByUsername(username);
	}

	@Override
	public List<User> getAllUsers() {
		return springDataForUsers.findAll();
	}

	@Override
	public void createUser(User user) {
		springDataForUsers.save(user);
	}
	
	@Override
	public void updateUser(User user) {
		springDataForUsers.save(user);
	}

	@Override
	public void deleteUser(UUID id) {
		springDataForUsers.deleteById(id);
	}

}