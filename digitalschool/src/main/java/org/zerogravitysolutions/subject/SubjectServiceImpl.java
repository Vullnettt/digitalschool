package org.zerogravitysolutions.subject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.zerogravitysolutions.subject.utils.SubjectMapper;
import org.zerogravitysolutions.training.TrainingDto;
import org.zerogravitysolutions.training.TrainingEntity;
import org.zerogravitysolutions.training.TrainingService;
import org.zerogravitysolutions.training.utils.TrainingMapper;

import java.sql.Timestamp;
import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;
    private final TrainingService trainingService;
    private final TrainingMapper trainingMapper;

    public SubjectServiceImpl(SubjectRepository subjectRepository, SubjectMapper subjectMapper, TrainingService trainingService, TrainingMapper trainingMapper) {
        this.subjectRepository = subjectRepository;
        this.subjectMapper = subjectMapper;
        this.trainingService = trainingService;
        this.trainingMapper = trainingMapper;
    }

    @Override
    public ResponseEntity<SubjectDto> save(SubjectDto subjectDto) {
        SubjectEntity subjectEntity = new SubjectEntity();
        TrainingEntity trainingEntity = new TrainingEntity();
        TrainingDto trainingDto = trainingService.findById(subjectDto.getTrainingId()).getBody();
        subjectEntity.setTraining(trainingMapper.mapDtoToEntityWithEntityReturnType(trainingDto, trainingEntity));
        subjectEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        subjectEntity.setCreatedBy(1L);
        subjectMapper.mapDtoToEntity(subjectDto, subjectEntity);
        return ResponseEntity.ok().body(subjectMapper.mapsEntityToDto(subjectRepository.save(subjectEntity), subjectDto));
    }

    @Override
    public ResponseEntity<List<SubjectDto>> findAll() {
        List<SubjectEntity> subjectEntities = subjectRepository.findByDeletedAtIsNull();
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
        TrainingEntity trainingEntity = new TrainingEntity();
        TrainingDto trainingDto = trainingService.findById(subjectDto.getTrainingId()).getBody();
        subjectEntity.setTraining(trainingMapper.mapDtoToEntityWithEntityReturnType(trainingDto, trainingEntity));
        subjectEntity.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        subjectEntity.setUpdatedBy(1L);
        subjectMapper.mapDtoToEntity(subjectDto, subjectEntity);
        return ResponseEntity.ok().body(subjectMapper.mapsEntityToDto(subjectRepository.save(subjectEntity), subjectDto));
    }

    @Override
    public ResponseEntity<SubjectDto> partialUpdate(Long id, SubjectDto subjectDto) {
        SubjectEntity subjectEntity = subjectRepository.findByIdAndDeletedAtIsNull(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject with id: " + id + " not found."));
        subjectEntity.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        subjectEntity.setUpdatedBy(1L);
        subjectMapper.mapDtoToEntity(subjectDto, subjectEntity);
        return ResponseEntity.ok().body(subjectMapper.mapsEntityToDto(subjectRepository.save(subjectEntity), subjectDto));
    }

    @Override
    public void deleteById(Long id) {
        SubjectEntity subjectEntity = subjectRepository.findByIdAndDeletedAtIsNull(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Subject with id: " + id + " not found."));
        subjectRepository.delete(subjectEntity);
    }
}
