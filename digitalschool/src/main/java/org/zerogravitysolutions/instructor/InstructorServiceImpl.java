package org.zerogravitysolutions.instructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.zerogravitysolutions.instructor.utils.InstructorMapper;

@Service
public class InstructorServiceImpl implements InstructorService{

    private final InstructorRepository instructorRepository;
    private final InstructorMapper instructorMapper;


    @Autowired
    public InstructorServiceImpl(InstructorRepository instructorRepository, InstructorMapper instructorMapper) {
        this.instructorRepository = instructorRepository;
        this.instructorMapper = instructorMapper;
    }


    @Override
    public ResponseEntity<InstructorDto> findById(Long id) {
        InstructorEntity instructorEntity = instructorRepository.findByIdAndDeletedAtIsNull(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Instructor with id: " + id + " not found"));
        return ResponseEntity.ok().body(instructorMapper.mapEntityToDto(instructorEntity));
    }
}
