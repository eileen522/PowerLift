package de.dhbw.softwareengineering.powerLift.domain.entities;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "workout_plan")
public class WorkoutPlan {
	@Id
	@Column(name = "id")
    private String id;
	
	@Column
	private String name;
	
    @Column
    private String description;
	
    @OneToMany(mappedBy = "workoutPlan")
    private List<Workout> workouts;
    
    public WorkoutPlan(String name, String description, List<Workout> workouts) {
    	this.id = UUID.randomUUID().toString();
    	this.name = name;
    	this.description = description;
    	this.workouts = workouts;
    }
    
    protected WorkoutPlan() {
    }
    
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
    	return description;
    }
    
    public List<Workout> getWorkouts() {
        return workouts;
    }

}