package de.dhbw.softwareengineering.powerLift.domain.entities;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.Validate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "workout_plans")
public class WorkoutPlan {
	@Id
	@Column(name = "id")
    private UUID id;
	
	@Column
	private String name;
	
    @Column
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
	
    @OneToMany(cascade = CascadeType.ALL)
    private List<Workout> workouts;
    
    public WorkoutPlan(String name, String description, User user, List<Workout> workouts) {
    	this.id = UUID.randomUUID();
    	this.name = name;
    	this.description = description;
    	this.workouts = workouts;
    }
    
    protected WorkoutPlan() {
    }
    
    public UUID getId() {
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
    
    public void setId(UUID id) {
        this.id = id;
    }
    
    public void setName(String name) {
        Validate.notBlank(name);
        this.name = name;
    }
    
    public void setDescription(String description) {
        Validate.notBlank(description);
        this.description = description;
    }
    
    public void setWorkouts(List<Workout> workouts) {
        this.workouts = workouts;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public User getUser() {
        return user;
    }
    
}