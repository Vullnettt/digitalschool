package org.zerogravitysolutions.group;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GroupService {
    ResponseEntity<GroupDto> save(GroupDto groupDto);

    ResponseEntity<List<GroupDto>> findAll();

    ResponseEntity<GroupDto> findById(Long id);

    ResponseEntity<GroupDto> update(GroupDto groupDto);

    ResponseEntity<GroupDto> partialUpdate(Long id, GroupDto groupDto);

    ResponseEntity<GroupDto> addStudent(Long groupId, Long studentId);

    ResponseEntity<GroupDto> addInstructor(Long groupId, Long instructorId);
}
