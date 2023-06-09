package org.zerogravitysolutions.instructor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.ManyToMany;
import org.zerogravitysolutions.commons.BaseDto;
import org.zerogravitysolutions.group.GroupEntity;
import org.zerogravitysolutions.instructor.disable_reason.DisableReason;
import org.zerogravitysolutions.training.TrainingEntity;

import java.util.HashSet;
import java.util.Set;

public class InstructorDto extends BaseDto {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String bio;
    private String facebookProfile;
    private String linkedinProfile;

    @JsonIgnore
    private byte[] profilePicture;

    @JsonIgnoreProperties("instructors")
    private Set<TrainingEntity> trainings = new HashSet<>();

    @JsonIgnoreProperties("instructors")
    private Set<GroupEntity> groups = new HashSet<>();

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

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
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
