package org.zerogravitysolutions.student;


import org.springframework.data.jpa.repository.JpaRepository;
import org.zerogravitysolutions.group.GroupEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    Optional<StudentEntity> findByIdAndDeletedAtIsNull(Long id);
    List<StudentEntity> findByDeletedAtIsNull();

    long countByGroupsIn(Set<GroupEntity> groupEntitySet);
}
