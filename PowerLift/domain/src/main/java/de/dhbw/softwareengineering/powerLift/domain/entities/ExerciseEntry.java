package de.dhbw.softwareengineering.powerLift.domain.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "exercise_entry")
public class ExerciseEntry {
    @Id
    @Column(name = "id")
    private String id;

    @ManyToOne
    private TrainingEntry trainingEntry;

    @ManyToOne
    private Exercise exercise;

    @Column
    private Integer sets;

    @Column
    private Integer reps;

    @Column
    private Integer rpe;

    public ExerciseEntry(TrainingEntry trainingEntry, Exercise exercise, Integer sets, Integer reps, Integer rpe) {
    	this.id = UUID.randomUUID().toString();
        this.trainingEntry = trainingEntry;
        this.exercise = exercise;
        this.sets = sets;
        this.reps = reps;
        this.rpe = rpe;
    }

    protected ExerciseEntry() {
    }

    public String getId() {
        return id;
    }

    public TrainingEntry getTrainingEntry() {
        return trainingEntry;
    }

    public Exercise getExercise() {
        return exercise;
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
