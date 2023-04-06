package org.zerogravitysolutions.subject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.zerogravitysolutions.subject.utils.SubjectMapper;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;

    public SubjectServiceImpl(SubjectRepository subjectRepository, SubjectMapper subjectMapper) {
        this.subjectRepository = subjectRepository;
        this.subjectMapper = subjectMapper;
    }

    @Override
    public ResponseEntity<SubjectDto> save(SubjectDto subjectDto) {
        SubjectEntity subjectEntity = new SubjectEntity();
        subjectMapper.mapDtoToEntity(subjectDto, subjectEntity);
        return ResponseEntity.ok().body(subjectMapper.mapEntityToDto(subjectRepository.save(subjectEntity)));
    }

    @Override
    public ResponseEntity<List<SubjectDto>> findAll() {
        List<SubjectEntity> subjectEntities = subjectRepository.findAll();
        return ResponseEntity.ok().body(subjectEntities.stream().map(subjectEntity -> subjectMapper.mapEntityToDto(subjectEntity)).toList());
    }

    @Override
    public ResponseEntity<SubjectDto> findById(Long id) {
        SubjectEntity subjectEntity = subjectRepository.findByIdAndDeletedAtIsNull(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject with id: " + id + " not found."));
        return ResponseEntity.ok().body(subjectMapper.mapEntityToDto(subjectEntity));
    }

    @Override
    public ResponseEntity<SubjectDto> update(SubjectDto subjectDto) {
        SubjectEntity subjectEntity = new SubjectEntity();
        subjectMapper.mapDtoToEntity(subjectDto, subjectEntity);
        return ResponseEntity.ok().body(subjectMapper.mapEntityToDto(subjectRepository.save(subjectEntity)));
    }
}
