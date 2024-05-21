package de.dhbw.softwareengineering.powerLift.domain.values;

import java.util.Objects;

import org.apache.commons.lang3.Validate;

import jakarta.persistence.Embeddable;

@Embeddable
public class RPE {
    private  double rpe;

    public RPE(double rpe) {
        setRpe(rpe);
    }

    public double getRpe() {
        return rpe;
    }

    public void setRpe(double rpe) {
        Validate.isTrue(rpe >= 0 && rpe <= 10, "RPE must be between 0 and 10");
        this.rpe = rpe;
    }
    
    public RPE()  {
		this.rpe = 0;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RPE rpe1 = (RPE) o;
        return Double.compare(rpe1.rpe, rpe) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rpe);
    }

    @Override
    public String toString() {
        return String.valueOf(rpe);
    }
}