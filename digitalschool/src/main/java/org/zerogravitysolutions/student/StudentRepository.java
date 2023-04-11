package org.zerogravitysolutions.student;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    Optional<StudentEntity> findByIdAndDeletedAtIsNull(Long id);
    List<StudentEntity> findByDeletedAtIsNull();
}
