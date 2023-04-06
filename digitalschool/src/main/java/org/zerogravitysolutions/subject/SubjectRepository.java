package org.zerogravitysolutions.subject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends JpaRepository<SubjectEntity, Long> {

    @Query("Select s From Subject As s Where s.deletedAt = null")
    List<SubjectEntity> findAllSubjectDeletedAtIsNull();

    Optional<SubjectEntity> findByIdAndDeletedAtIsNull(Long id);
}
