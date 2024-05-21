package de.dhbw.softwareengineering.powerLift.domain.entities;

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
    
    public User(Builder builder) {
  
        this.id = builder.id;
        this.username = builder.username;
        this.email = builder.email;
        this.password = builder.password;
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
    
    public static class Builder {
        private UUID id;
        private String username;
        private String email;
        private String password;

        public Builder() {
            this.id = UUID.randomUUID();
        }

        public Builder withUsername(String username) {
            Validate.notBlank(username);
            this.username = username;
            return this;
        }

        public Builder withEmail(String email) {
            Validate.notBlank(email);
            Validate.isTrue(EmailValidator.getInstance().isValidEmail(email));
            this.email = email;
            return this;
        }

        public Builder withPassword(String password) {
            Validate.notBlank(password);
            this.password = password;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
