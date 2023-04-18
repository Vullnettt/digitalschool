package org.zerogravitysolutions.instructor;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.zerogravitysolutions.instructor.disable_reason.DisableReason;

import java.util.List;

public interface InstructorService {
    ResponseEntity<InstructorDto> save(InstructorDto instructorDto);

    ResponseEntity<List<InstructorDto>> findAll();

    ResponseEntity<InstructorDto> findById(Long id);

    ResponseEntity<InstructorDto> update(InstructorDto instructorDto);

    ResponseEntity<InstructorDto> partialUpdate(Long id, InstructorDto instructorDto);

    ResponseEntity<InstructorDto> disable(Long id, DisableReason disableReason);

    ResponseEntity<InstructorDto> uploadProfilePicture(Long id, MultipartFile file);

    ResponseEntity<Resource> readProfilePicture(Long id);
}
