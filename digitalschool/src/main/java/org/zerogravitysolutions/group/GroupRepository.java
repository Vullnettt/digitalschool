package org.zerogravitysolutions.group;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<GroupEntity, Long> {

    Optional<GroupEntity> findByIdAndDeletedAtIsNull(Long id);
    List<GroupEntity> findByDeletedAtIsNull();
}
