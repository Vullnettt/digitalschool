package org.zerogravitysolutions.training;

import org.springframework.http.ResponseEntity;

public interface TrainingService {
    ResponseEntity<TrainingDto> save(TrainingDto trainingDto);
}
