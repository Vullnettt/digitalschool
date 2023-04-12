package org.zerogravitysolutions.instructor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.zerogravitysolutions.commons.BaseEntity;
import org.zerogravitysolutions.group.GroupEntity;
import org.zerogravitysolutions.instructor.disable_reason.DisableReason;
import org.zerogravitysolutions.training.TrainingEntity;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "instructors")
public class InstructorEntity extends BaseEntity {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String bio;
    private String facebookProfile;
    private String linkedinProfile;
    private String profilePicture;

    @ManyToMany(mappedBy = "instructors")
    @JsonIgnoreProperties("instructors")
    private Set<TrainingEntity> trainings = new HashSet<>();

    @ManyToMany(mappedBy = "instructors")
    @JsonIgnoreProperties("instructors")
    private Set<GroupEntity> groups = new HashSet<>();

    @OneToMany(mappedBy = "instructor")
    private Set<DisableReason> disableReasons = new HashSet<>();

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getFacebookProfile() {
        return facebookProfile;
    }

    public void setFacebookProfile(String facebookProfile) {
        this.facebookProfile = facebookProfile;
    }

    public String getLinkedinProfile() {
        return linkedinProfile;
    }

    public void setLinkedinProfile(String linkedinProfile) {
        this.linkedinProfile = linkedinProfile;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Set<TrainingEntity> getTrainings() {
        return trainings;
    }

    public void setTrainings(Set<TrainingEntity> trainings) {
        this.trainings = trainings;
    }

    public Set<GroupEntity> getGroups() {
        return groups;
    }

    public void setGroups(Set<GroupEntity> groups) {
        this.groups = groups;
    }

    public Set<DisableReason> getDisableReasons() {
        return disableReasons;
    }

    public void setDisableReasons(Set<DisableReason> disableReasons) {
        this.disableReasons = disableReasons;
    }
}
