package de.dhbw.softwareengineering.powerLift.domain.entities;

import java.util.UUID;

import org.apache.commons.lang3.Validate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "exercises")
public class Exercise {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column
    private String name;
    
    @Column
    private String description;
    
    @Column
    private String category;
    
    public Exercise(String name, String description, String category) {
    	this.id = UUID.randomUUID();
    	this.name = name;
        this.description = description;
        this.category = category;
    }
    
    protected Exercise() {
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
    
    public String getCategory() {
        return category;
    }

	public void setName(String name) {
        Validate.notBlank(name);
        this.name = name;
    }

    public void setDescription(String description) {
        Validate.notBlank(description);
        this.description = description;
    }

    public void setCategory(String category) {
        Validate.notBlank(category);
        this.category = category;
    }
}