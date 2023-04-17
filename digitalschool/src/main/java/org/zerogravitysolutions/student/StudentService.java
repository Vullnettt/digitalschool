package org.zerogravitysolutions.student;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.zerogravitysolutions.group.GroupEntity;

import java.util.List;
import java.util.Set;

public interface StudentService {
    ResponseEntity<StudentDto> save(StudentDto studentDto);

    ResponseEntity<List<StudentDto>> findAll();

    ResponseEntity<StudentDto> findById(Long id);

    ResponseEntity<StudentDto> update(StudentDto studentDto);

    ResponseEntity<StudentDto> disable(Long id);

    ResponseEntity<?> enable(Long id);

    ResponseEntity<StudentDto> uploadProfilePicture(Long id, MultipartFile file);

    ResponseEntity<Resource> readProfilePicture(Long id);

    long countByGroupsIn(Set<GroupEntity> groupEntitySet);
}
