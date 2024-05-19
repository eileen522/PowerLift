package de.dhbw.softwareengineering.powerLift.domain.entities;

import java.util.List;
import java.util.UUID;

import de.dhbw.softwareengineering.powerLift.domain.entities.user.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "workout")
public class Workout {
    @Id
    @Column(name = "id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL)
    private List<TrainingSet> trainingSets;
    
    @ManyToOne
    @JoinColumn(name = "workout_plan_id")
    private WorkoutPlan workoutPlan;
    
    public Workout(User user, List<TrainingSet> trainingSets) {
    	this.id = UUID.randomUUID().toString();
    	this.user = user;
    	this.trainingSets = trainingSets;
    }
    
    protected Workout() {
    	
    }
    
    public String getId() {
        return id;
    }
    
    public User getUser() {
    	return user;
    }
    
    public List<TrainingSet> getTrainingSets() {
    	return trainingSets;
    }
    
    public WorkoutPlan getWorkoutPlan() {
    	return workoutPlan;
    }
 
}