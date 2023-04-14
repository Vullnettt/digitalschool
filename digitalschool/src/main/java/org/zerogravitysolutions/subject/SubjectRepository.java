package org.zerogravitysolutions.subject;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends JpaRepository<SubjectEntity, Long> {
    Optional<SubjectEntity> findByIdAndDeletedAtIsNull(Long id);
    List<SubjectEntity> findByDeletedAtIsNull();
    void deleteById(Long id);
}
