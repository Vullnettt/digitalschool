package org.zerogravitysolutions.training;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TrainingRepository extends JpaRepository<TrainingEntity, Long> {
    Optional<TrainingEntity> findByIdAndDeletedAtIsNull(Long id);
    List<TrainingEntity> findByDeletedAtIsNull();
    Page<TrainingEntity> findAllByDeletedAtIsNull(Pageable pageable);
}
