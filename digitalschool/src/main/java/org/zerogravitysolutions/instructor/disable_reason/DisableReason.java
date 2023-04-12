package org.zerogravitysolutions.instructor.disable_reason;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.zerogravitysolutions.commons.BaseEntity;
import org.zerogravitysolutions.instructor.InstructorEntity;

@Entity
@Table(name = "disable_reason")
public class DisableReason extends BaseEntity {

    private String disableReason;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    @JsonIgnore
    private InstructorEntity instructor;


    public String getDisableReason() {
        return disableReason;
    }

    public void setDisableReason(String disableReason) {
        this.disableReason = disableReason;
    }

    public InstructorEntity getInstructor() {
        return instructor;
    }

    public void setInstructor(InstructorEntity instructor) {
        this.instructor = instructor;
    }
}
