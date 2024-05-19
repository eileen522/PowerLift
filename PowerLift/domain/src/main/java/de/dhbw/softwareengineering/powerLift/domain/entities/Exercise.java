package de.dhbw.softwareengineering.powerLift.domain.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "exercises")
public class Exercise {
    @Id
    @Column(name = "id")
    private String id;

    @Column
    private String name;
    
    @Column
    private String description;
    
    @Column
    private String category;
    
    public Exercise(String name, String description, String category) {
    	this.id = UUID.randomUUID().toString();
    	this.name = name;
        this.description = description;
        this.category = category;
    }
    
    protected Exercise() {
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
    
    public String getCategory() {
        return category;
    }
}