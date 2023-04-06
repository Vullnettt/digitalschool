package org.zerogravitysolutions.subject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.zerogravitysolutions.commons.BaseEntity;
import org.zerogravitysolutions.training.TrainingEntity;

@Entity
@Table(name = "subjects")
public class SubjectEntity extends BaseEntity {

    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "training_id")
    @JsonIgnore
    private TrainingEntity training;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TrainingEntity getTraining() {
        return training;
    }

    public void setTraining(TrainingEntity training) {
        this.training = training;
    }
}
