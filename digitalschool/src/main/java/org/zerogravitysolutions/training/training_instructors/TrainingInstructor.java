package org.zerogravitysolutions.training.training_instructors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.zerogravitysolutions.commons.BaseEntity;
import org.zerogravitysolutions.instructor.InstructorEntity;
import org.zerogravitysolutions.training.TrainingEntity;

@Entity
@Table(name = "training_instructors")
public class TrainingInstructor extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "training_id")
    @JsonIgnore
    private TrainingEntity training;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    @JsonIgnore
    private InstructorEntity instructor;

    public TrainingEntity getTraining() {
        return training;
    }

    public void setTraining(TrainingEntity training) {
        this.training = training;
    }

    public InstructorEntity getInstructor() {
        return instructor;
    }

    public void setInstructor(InstructorEntity instructor) {
        this.instructor = instructor;
    }
}
