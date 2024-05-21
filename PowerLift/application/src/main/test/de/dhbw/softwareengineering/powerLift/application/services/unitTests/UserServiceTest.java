package de.dhbw.softwareengineering.powerLift.application.services.unitTests;

import de.dhbw.softwareengineering.powerLift.application.services.user.UserAlreadyExistsException;
import de.dhbw.softwareengineering.powerLift.application.services.user.UserDeletedEvent;
import de.dhbw.softwareengineering.powerLift.application.services.user.UserService;
import de.dhbw.softwareengineering.powerLift.domain.entities.User;
import de.dhbw.softwareengineering.powerLift.domain.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @InjectMocks
    private UserService userService;

    private User user;
    private UUID userId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userId = UUID.randomUUID();
        user = new User.Builder()
    	        .withUsername("testUsername")
    	        .withEmail("test@example.com")
    	        .withPassword("password")
    	        .build();
    	}
    @Test
    void testGetUserById() {
        when(userRepository.getUserById(userId)).thenReturn(Optional.of(user));

        Optional<User> foundUser = userService.getUserById(userId);

        assertTrue(foundUser.isPresent());
        assertEquals(user, foundUser.get());
    }

    @Test
    void testGetUserByEmail() {
        when(userRepository.getUserByEmail("test@example.com")).thenReturn(Optional.of(user));

        Optional<User> foundUser = userService.getUserByEmail("test@example.com");

        assertTrue(foundUser.isPresent());
        assertEquals(user, foundUser.get());
    }

    @Test
    void testGetUserByUsername() {
        when(userRepository.getUserByUsername("testUsername")).thenReturn(Optional.of(user));

        Optional<User> foundUser = userService.getUserByUsername("testUsername");

        assertTrue(foundUser.isPresent());
        assertEquals(user, foundUser.get());
    }

    @Test
    void testGetAllUsers() {
        List<User> users = List.of(user);
        when(userRepository.getAllUsers()).thenReturn(users);

        List<User> foundUsers = userService.getAllUsers();

        assertEquals(users, foundUsers);
    }

    @Test
    void testCreateUser() {
        when(userRepository.getUserByUsername("testUsername")).thenReturn(Optional.empty());
        when(userRepository.getUserByEmail("test@example.com")).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> userService.create(user));
        verify(userRepository, times(1)).createUser(any(User.class));
    }

    @Test
    void testCreateUserThrowsExceptionWhenUsernameExists() {
        when(userRepository.getUserByUsername("testUsername")).thenReturn(Optional.of(user));

        assertThrows(UserAlreadyExistsException.class, () -> userService.create(user));
        verify(userRepository, never()).createUser(any(User.class));
    }

    @Test
    void testCreateUserThrowsExceptionWhenEmailExists() {
        when(userRepository.getUserByEmail("test@example.com")).thenReturn(Optional.of(user));

        assertThrows(UserAlreadyExistsException.class, () -> userService.create(user));
        verify(userRepository, never()).createUser(any(User.class));
    }

    @Test
    void testUpdateUser() {
        when(userRepository.getUserById(userId)).thenReturn(Optional.of(user));
        when(userRepository.getUserByUsername("newUsername")).thenReturn(Optional.empty());
        when(userRepository.getUserByEmail("new@example.com")).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> userService.updateUser(userId, "newUsername", "new@example.com", "newPassword"));
        verify(userRepository, times(1)).updateUser(any(User.class));
    }

    @Test
    void testUpdateUserThrowsExceptionWhenUsernameExists() {
        User anotherUser = new User.Builder()
    	        .withUsername("newUsername")
    	        .withEmail("another@example.com")
    	        .withPassword("password")
    	        .build();

        when(userRepository.getUserById(userId)).thenReturn(Optional.of(user));
        when(userRepository.getUserByUsername("newUsername")).thenReturn(Optional.of(anotherUser));

        assertThrows(UserAlreadyExistsException.class, () -> userService.updateUser(userId, "newUsername", "test@example.com", "newPassword"));
        verify(userRepository, never()).updateUser(any(User.class));
    }

    @Test
    void testUpdateUserThrowsExceptionWhenEmailExists() {
        User anotherUser = new User.Builder()
    	        .withUsername("anotherUsername")
    	        .withEmail("new@example.com")
    	        .withPassword("password")
    	        .build();
        when(userRepository.getUserById(userId)).thenReturn(Optional.of(user));
        when(userRepository.getUserByEmail("new@example.com")).thenReturn(Optional.of(anotherUser));

        assertThrows(UserAlreadyExistsException.class, () -> userService.updateUser(userId, "testUsername", "new@example.com", "newPassword"));
        verify(userRepository, never()).updateUser(any(User.class));
    }

    @Test
    void testUpdateUserThrowsExceptionWhenUserNotFound() {
        when(userRepository.getUserById(userId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> userService.updateUser(userId, "newUsername", "new@example.com", "newPassword"));
        verify(userRepository, never()).updateUser(any(User.class));
    }

    @Test
    void testDeleteUser() {
        doNothing().when(userRepository).deleteUser(userId);

        userService.deleteUser(userId);

        verify(userRepository, times(1)).deleteUser(userId);
        verify(eventPublisher, times(1)).publishEvent(any(UserDeletedEvent.class));
    }
}