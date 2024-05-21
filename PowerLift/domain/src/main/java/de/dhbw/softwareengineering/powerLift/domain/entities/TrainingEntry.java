package de.dhbw.softwareengineering.powerLift.domain.entities;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "training_entry")
public class TrainingEntry {
    @Id
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column
    private Date date;

    @OneToMany(mappedBy = "trainingEntry")
    private List<ExerciseEntry> exerciseEntries;

    public TrainingEntry(User user, Date date, List<ExerciseEntry> exerciseEntries) {
    	this.id = UUID.randomUUID();
        this.user = user;
        this.date = date;
        this.exerciseEntries = exerciseEntries;
    }

    protected TrainingEntry() {
    }

    public UUID getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Date getDate() {
        return date;
    }

    public List<ExerciseEntry> getExerciseEntries() {
        return exerciseEntries;
    }
}
