package org.zerogravitysolutions.training;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.zerogravitysolutions.commons.BaseEntity;
import org.zerogravitysolutions.instructor.InstructorEntity;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "trainings")
public class TrainingEntity extends BaseEntity {

    private String title;
    private String description;
    private String price;
    private String coverImageFileName;

    @ManyToMany
    @JoinTable (
            name = "training_instructors",
            joinColumns = { @JoinColumn(name = "training_id") },
            inverseJoinColumns = { @JoinColumn(name = "instructor_id") }
    )
    @JsonIgnoreProperties("trainings")
    private Set<InstructorEntity> instructors = new HashSet<>();


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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCoverImageFileName() {
        return coverImageFileName;
    }

    public void setCoverImageFileName(String coverImageFileName) {
        this.coverImageFileName = coverImageFileName;
    }

    public Set<InstructorEntity> getInstructors() {
        return instructors;
    }

    public void setInstructors(Set<InstructorEntity> instructors) {
        this.instructors = instructors;
    }
}
