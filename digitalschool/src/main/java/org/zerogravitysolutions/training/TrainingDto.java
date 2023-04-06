package org.zerogravitysolutions.training;

import org.zerogravitysolutions.commons.BaseDto;
import org.zerogravitysolutions.subject.SubjectEntity;

import java.util.HashSet;
import java.util.Set;

public class TrainingDto extends BaseDto {

    private String title;
    private String description;
    private Double price;
    private String coverImageFileName;

    private Set<SubjectEntity> subjects = new HashSet<>();

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
}
