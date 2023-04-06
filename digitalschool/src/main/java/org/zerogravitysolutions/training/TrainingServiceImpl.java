package org.zerogravitysolutions.training;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.zerogravitysolutions.training.utils.TrainingMapper;

@Service
public class TrainingServiceImpl implements TrainingService {

    private final TrainingRepository trainingRepository;
    private final TrainingMapper trainingMapper;


    public TrainingServiceImpl(TrainingRepository trainingRepository, TrainingMapper trainingMapper) {
        this.trainingRepository = trainingRepository;
        this.trainingMapper = trainingMapper;
    }

    @Override
    public ResponseEntity<TrainingDto> save(TrainingDto trainingDto) {
        TrainingEntity training = new TrainingEntity();
        trainingMapper.mapEntityToDto(training, trainingDto);
        return ResponseEntity.ok().body(trainingMapper.mapEntityToDto(trainingRepository.save(training)));
    }
}
