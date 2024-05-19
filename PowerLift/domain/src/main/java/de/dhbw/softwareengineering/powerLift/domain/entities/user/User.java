package de.dhbw.softwareengineering.powerLift.domain.entities.user;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.apache.commons.lang3.Validate;

import de.dhbw.softwareengineering.powerLift.domain.validation.EmailValidator;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column
    private String username;
    
    @Column
    private String email;

    @Column
    private String password;
    
    public User(String username, String email, String password) {
        Validate.notBlank(username);
        Validate.notBlank(email);
        Validate.isTrue(EmailValidator.getInstance().isValidEmail(email));
        Validate.notBlank(password);
    	
    	this.id = UUID.randomUUID();
        this.username = username;
        this.email = email;
        this.password = password;
    }
    
    protected User() {
    }
    
    public UUID getId() {
        return id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    
    public void setUsername(String username) {
        Validate.notBlank(username);
        this.username = username;
    }

    public void setEmail(String email) {
        Validate.notBlank(email);
        Validate.isTrue(EmailValidator.getInstance().isValidEmail(email));
        this.email = email;
    }

    public void setPassword(String password) {
        Validate.notBlank(password);
        this.password = password;
    }
}
