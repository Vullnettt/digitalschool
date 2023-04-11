package org.zerogravitysolutions.training;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TrainingRepository extends JpaRepository<TrainingEntity, Long> {
    Optional<TrainingEntity> findByIdAndDeletedAtIsNull(Long id);
    List<TrainingEntity> findByDeletedAtIsNull();
}
