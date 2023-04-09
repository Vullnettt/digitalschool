package org.zerogravitysolutions.instructor;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InstructorService {
    ResponseEntity<InstructorDto> save(InstructorDto instructorDto);

    ResponseEntity<List<InstructorDto>> findAll();

    ResponseEntity<InstructorDto> findById(Long id);

    ResponseEntity<InstructorDto> update(InstructorDto instructorDto);

    ResponseEntity<InstructorDto> partialUpdate(Long id, InstructorDto instructorDto);
}
