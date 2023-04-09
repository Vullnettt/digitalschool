package org.zerogravitysolutions.instructor;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstructorRepository extends JpaRepository<InstructorEntity, Long> {
    Optional<InstructorEntity> findByIdAndDeletedAtIsNull(Long id);
}
