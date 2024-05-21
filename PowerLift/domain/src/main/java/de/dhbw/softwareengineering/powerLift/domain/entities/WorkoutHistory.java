package de.dhbw.softwareengineering.powerLift.domain.entities;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "workout_history")
public class WorkoutHistory {
	@Id
	@Column(name = "id")
    private UUID id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "workout_id", nullable = false)
    private Workout workout;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    protected WorkoutHistory() {
    }

    public WorkoutHistory(Workout workout, User user, LocalDate date) {
        this.id = UUID.randomUUID();
        this.workout = workout;
        this.user = user;
        this.date = date;
    }

    public UUID getId() {
        return id;
    }

    public Workout getWorkout() {
        return workout;
    }
    
    public User getUser() {
        return user;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public LocalDate getDate() {
        return date;
    }
    
    public void setUser(User user) {
    	this.user = user;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
