package org.zerogravitysolutions.training;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.zerogravitysolutions.commons.BaseEntity;
import org.zerogravitysolutions.group.GroupEntity;
import org.zerogravitysolutions.instructor.InstructorEntity;
import org.zerogravitysolutions.subject.SubjectEntity;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "trainings")
public class TrainingEntity extends BaseEntity {

    private String title;
    private String description;
    private Double price;
    @Column(name = "cover_image_filename")
    private String coverImageFileName;

    @ManyToMany
    @JoinTable (
            name = "training_instructors",
            joinColumns = { @JoinColumn(name = "training_id") },
            inverseJoinColumns = { @JoinColumn(name = "instructor_id") }
    )
    @JsonIgnoreProperties("trainings")
    private Set<InstructorEntity> instructors = new HashSet<>();

    @OneToMany(mappedBy = "training")
    private Set<SubjectEntity> subjects = new HashSet<>();

    @OneToMany(mappedBy = "training")
    private Set<GroupEntity> groups = new HashSet<>();


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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
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

    public Set<SubjectEntity> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<SubjectEntity> subjects) {
        this.subjects = subjects;
    }

    public Set<GroupEntity> getGroups() {
        return groups;
    }

    public void setGroups(Set<GroupEntity> groups) {
        this.groups = groups;
    }
}
