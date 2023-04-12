package org.zerogravitysolutions.training.training_instructors;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingInstructorRepository extends JpaRepository<TrainingInstructor, Long> {
    void deleteByInstructorId(Long id);
}