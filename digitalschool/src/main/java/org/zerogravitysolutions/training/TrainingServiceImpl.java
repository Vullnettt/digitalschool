package org.zerogravitysolutions.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.zerogravitysolutions.training.utils.TrainingMapper;

import java.sql.Timestamp;
import java.util.List;

@Service
public class TrainingServiceImpl implements TrainingService {

    private final TrainingRepository trainingRepository;
    private final TrainingMapper trainingMapper;

    @Autowired
    public TrainingServiceImpl(TrainingRepository trainingRepository, TrainingMapper trainingMapper) {
        this.trainingRepository = trainingRepository;
        this.trainingMapper = trainingMapper;
    }

    @Override
    public ResponseEntity<TrainingDto> save(TrainingDto trainingDto) {
        TrainingEntity training = new TrainingEntity();
        training.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        training.setCreatedBy(1L);
        trainingMapper.mapDtoToEntity(trainingDto, training);
        return ResponseEntity.ok().body(trainingMapper.mapEntityToDto(trainingRepository.save(training)));
    }

    @Override
    public ResponseEntity<List<TrainingDto>> findAll() {
        List<TrainingEntity> trainingEntities = trainingRepository.findAll();
        return ResponseEntity.ok().body(trainingEntities.stream().map(trainingEntity -> trainingMapper.mapEntityToDto(trainingEntity)).toList());
    }

    @Override
    public ResponseEntity<TrainingDto> findById(Long id) {
        TrainingEntity trainingEntity = trainingRepository.findByIdAndDeletedAtIsNull(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Training with id: " + id + " not found."));
        return ResponseEntity.ok().body(trainingMapper.mapEntityToDto(trainingEntity));
    }

    @Override
    public ResponseEntity<TrainingDto> update(TrainingDto trainingDto) {
        TrainingEntity trainingEntity = new TrainingEntity();
        trainingEntity.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        trainingEntity.setCreatedBy(1L);
        trainingMapper.mapDtoToEntity(trainingDto, trainingEntity);
        return ResponseEntity.ok().body(trainingMapper.mapEntityToDto(trainingRepository.save(trainingEntity)));
    }
}
