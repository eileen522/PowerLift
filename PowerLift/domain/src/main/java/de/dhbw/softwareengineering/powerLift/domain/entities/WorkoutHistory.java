package de.dhbw.softwareengineering.powerLift.domain.entities;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "workout_history")
public class WorkoutHistory {
	@Id
	@Column(name = "id")
    private UUID id;
	
	@ManyToOne(cascade = CascadeType.ALL)
    private Workout workout;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    protected WorkoutHistory() {
    }

    public WorkoutHistory(Workout workout, LocalDate date) {
        this.id = UUID.randomUUID();
        this.workout = workout;
        this.date = date;
    }

    public UUID getId() {
        return id;
    }

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
