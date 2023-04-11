package org.zerogravitysolutions.group;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.zerogravitysolutions.commons.BaseDto;
import org.zerogravitysolutions.instructor.InstructorEntity;
import org.zerogravitysolutions.student.StudentEntity;
import org.zerogravitysolutions.training.TrainingEntity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class GroupDto extends BaseDto {

    private String title;
    private String description;
    private Timestamp startDate;
    private Timestamp endDate;
    private Long trainingId;

    @JsonIgnoreProperties("groups")
    private Set<StudentEntity> students = new HashSet<>();

    @JsonIgnoreProperties("groups")
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

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public Long getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(Long trainingId) {
        this.trainingId = trainingId;
    }

    public Set<StudentEntity> getStudents() {
        return students;
    }

    public void setStudents(Set<StudentEntity> students) {
        this.students = students;
    }

    public Set<InstructorEntity> getInstructors() {
        return instructors;
    }

    public void setInstructors(Set<InstructorEntity> instructors) {
        this.instructors = instructors;
    }
}
