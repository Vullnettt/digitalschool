package org.zerogravitysolutions.instructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.zerogravitysolutions.instructor.utils.InstructorMapper;

import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional
public class InstructorServiceImpl implements InstructorService{

    private final InstructorRepository instructorRepository;
    private final InstructorMapper instructorMapper;


    @Autowired
    public InstructorServiceImpl(InstructorRepository instructorRepository, InstructorMapper instructorMapper) {
        this.instructorRepository = instructorRepository;
        this.instructorMapper = instructorMapper;
    }

    @Override
    public ResponseEntity<InstructorDto> save(InstructorDto instructorDto) {
        InstructorEntity instructorEntity = new InstructorEntity();
        instructorEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        instructorEntity.setCreatedBy(1L);
        instructorMapper.mapDtoToEntity(instructorDto, instructorEntity);
        return ResponseEntity.ok().body(instructorMapper.mapsEntityToDto(instructorRepository.save(instructorEntity), instructorDto));
    }

    @Override
    public ResponseEntity<List<InstructorDto>> findAll() {
        List<InstructorEntity> instructorEntities = instructorRepository.findAll();
        return ResponseEntity.ok().body(instructorEntities.stream().map(instructorEntity -> instructorMapper.mapEntityToDto(instructorEntity)).toList());
    }

    @Override
    public ResponseEntity<InstructorDto> findById(Long id) {
        InstructorEntity instructorEntity = instructorRepository.findByIdAndDeletedAtIsNull(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Instructor with id: " + id + " not found"));
        return ResponseEntity.ok().body(instructorMapper.mapEntityToDto(instructorEntity));
    }
    @Override
    public ResponseEntity<InstructorDto> update(InstructorDto instructorDto) {
        InstructorEntity instructorEntity = new InstructorEntity();
        instructorEntity.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        instructorEntity.setUpdatedBy(1L);
        instructorMapper.mapDtoToEntity(instructorDto, instructorEntity);
        return ResponseEntity.ok().body(instructorMapper.mapsEntityToDto(instructorRepository.save(instructorEntity), instructorDto));    }

    @Override
    public ResponseEntity<InstructorDto> partialUpdate(Long id, InstructorDto instructorDto) {
        InstructorEntity instructorEntity = instructorRepository.findByIdAndDeletedAtIsNull(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Instructor with id: " + id + " not found"));

        instructorEntity.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        instructorEntity.setUpdatedBy(1L);

        instructorMapper.mapDtoToEntity(instructorDto, instructorEntity);
        return ResponseEntity.ok().body(instructorMapper.mapsEntityToDto(instructorRepository.save(instructorEntity), instructorDto));
    }
}
