package de.dhbw.softwareengineering.powerLift.domain.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "training_sets")
public class TrainingSet {
    @Id
    @Column(name = "id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "workout_id")
    private Workout workout;

    @Column
    private Integer sets;

    @Column
    private Integer reps;
    
    @Column
    private Integer rpe;

    public TrainingSet(Workout workout, Integer sets, Integer reps, Integer rpe) {
    	this.id = UUID.randomUUID().toString();
        this.workout = workout;
        this.sets = sets;
        this.reps = reps;
        this.rpe = rpe;
    }

    protected TrainingSet() {
    }

    public String getId() {
        return id;
    }
    
    public Workout getWorkout() {
    	return workout;
    }

    public Integer getSets() {
        return sets;
    }

    public Integer getReps() {
        return reps;
    }
    
    public Integer getRpe() {
        return rpe;
    }
}