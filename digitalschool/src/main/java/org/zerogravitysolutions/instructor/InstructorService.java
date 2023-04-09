package org.zerogravitysolutions.instructor;

import org.springframework.http.ResponseEntity;

public interface InstructorService {

    ResponseEntity<InstructorDto> findById(Long id);
}
