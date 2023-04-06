package org.zerogravitysolutions.group.group_intructors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.zerogravitysolutions.commons.BaseEntity;
import org.zerogravitysolutions.group.GroupEntity;
import org.zerogravitysolutions.instructor.InstructorEntity;

@Entity
@Table(name = "group_instructors")
public class GroupInstructor extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "group_id")
    @JsonIgnore
    private GroupEntity group;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    @JsonIgnore
    private InstructorEntity instructor;


    public GroupEntity getGroup() {
        return group;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
    }

    public InstructorEntity getInstructor() {
        return instructor;
    }

    public void setInstructor(InstructorEntity instructor) {
        this.instructor = instructor;
    }
}
