package de.dhbw.softwareengineering.powerLift.domain.entities;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "workouts")
public class Workout {
	@Id
    @Column(name = "id")
    private UUID id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    private List<TrainingSet> trainingSets;

    public Workout(String name, User user, List<TrainingSet> trainingSets) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.user = user;
        this.trainingSets = trainingSets;
    }

    protected Workout() {
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User getUser() {
        return user;
    }

    public List<TrainingSet> getTrainingSets() {
        return trainingSets;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTrainingSets(List<TrainingSet> trainingSets) {
        this.trainingSets = trainingSets;
    }
}