package org.zerogravitysolutions.student;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StudentService {
    ResponseEntity<StudentDto> save(StudentDto studentDto);

    ResponseEntity<List<StudentDto>> findAll();

    ResponseEntity<StudentDto> findById(Long id);

    ResponseEntity<StudentDto> update(StudentDto studentDto);

    ResponseEntity<StudentDto> disable(Long id);

    ResponseEntity<?> enable(Long id);

    ResponseEntity<StudentDto> uploadProfilePicture(Long id, MultipartFile file);

    ResponseEntity<Resource> readProfilePicture(Long id);
}
