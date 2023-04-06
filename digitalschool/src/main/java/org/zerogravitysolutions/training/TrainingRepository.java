package org.zerogravitysolutions.training;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TrainingRepository extends JpaRepository<TrainingEntity, Long> {

    @Query("Select t From Training As t Where t.deletedAt = null")
    List<TrainingEntity> findAllTrainingDeletedAtIsNull();

    Optional<TrainingEntity> findByIdAndDeletedAtIsNull(Long id);
}
