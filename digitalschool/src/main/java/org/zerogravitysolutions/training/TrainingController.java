package org.zerogravitysolutions.training;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrainingController {

    private final TrainingService trainingService;

    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @PostMapping(path = "/trainings")
    public ResponseEntity<TrainingDto> save(@RequestBody TrainingDto trainingDto){
        return trainingService.save(trainingDto);
    }
}
