package org.zerogravitysolutions.student;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentService {
    ResponseEntity<StudentDto> save(StudentDto studentDto);

    ResponseEntity<List<StudentDto>> findAll();

    ResponseEntity<StudentDto> findById(Long id);

    ResponseEntity<StudentDto> update(StudentDto studentDto);
}
