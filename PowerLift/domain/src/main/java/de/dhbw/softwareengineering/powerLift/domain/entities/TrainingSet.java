package de.dhbw.softwareengineering.powerLift.domain.entities;

import java.util.UUID;

import de.dhbw.softwareengineering.powerLift.domain.values.RPE;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
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
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    @Column
    private Integer sets;

    //TODO: ValueObject with weight
    @Column
    private Integer reps;

    @Embedded
    private RPE rpe;

    public TrainingSet(Exercise exercise, Integer sets, Integer reps, RPE rpe) {
        this.id = UUID.randomUUID();
        this.exercise = exercise;
        this.sets = sets;
        this.reps = reps;
        this.rpe = rpe;
    }

    protected TrainingSet() {
    }

    public UUID getId() {
        return id;
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

    

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public void setSets(Integer sets) {
        this.sets = sets;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

	public RPE getRpe() {
		return rpe;
	}

	public void setRpe(RPE rpe) {
		this.rpe = rpe;
	}

    
}
