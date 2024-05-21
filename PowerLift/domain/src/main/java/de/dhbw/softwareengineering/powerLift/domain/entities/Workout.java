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
@Table(name = "workout")
public class Workout {
    @Id
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL)
    private List<TrainingSet> trainingSets;
    
    public Workout(User user, List<TrainingSet> trainingSets) {
    	this.id = UUID.randomUUID();
    	this.user = user;
    	this.trainingSets = trainingSets;
    }
    
    protected Workout() {
    	
    }
    
    public UUID getId() {
        return id;
    }
    
    public User getUser() {
    	return user;
    }
    
    public List<TrainingSet> getTrainingSets() {
    	return trainingSets;
    }
 
}