package org.zerogravitysolutions.training;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TrainingService {
    ResponseEntity<TrainingDto> save(TrainingDto trainingDto);

    ResponseEntity<List<TrainingDto>> findAll();

    ResponseEntity<TrainingDto> findById(Long id);

    ResponseEntity<TrainingDto> update(TrainingDto trainingDto);
}
