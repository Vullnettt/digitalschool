package org.zerogravitysolutions.training;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.zerogravitysolutions.commons.BaseDto;
import org.zerogravitysolutions.group.GroupEntity;
import org.zerogravitysolutions.instructor.InstructorEntity;
import org.zerogravitysolutions.subject.SubjectEntity;

import java.util.HashSet;
import java.util.Set;

public class TrainingDtoPageable extends BaseDto {

    private String title;
    private String description;
    private Double price;
    private String coverImageFileName;
    private long studentCount;

    private Set<SubjectEntity> subjects = new HashSet<>();

    @JsonIgnoreProperties("trainings")
    private Set<InstructorEntity> instructors = new HashSet<>();

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

    public Set<SubjectEntity> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<SubjectEntity> subjects) {
        this.subjects = subjects;
    }

    public long getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(long studentCount) {
        this.studentCount = studentCount;
    }

    public Set<InstructorEntity> getInstructors() {
        return instructors;
    }

    public void setInstructors(Set<InstructorEntity> instructors) {
        this.instructors = instructors;
    }

    public Set<GroupEntity> getGroups() {
        return groups;
    }

    public void setGroups(Set<GroupEntity> groups) {
        this.groups = groups;
    }
}
