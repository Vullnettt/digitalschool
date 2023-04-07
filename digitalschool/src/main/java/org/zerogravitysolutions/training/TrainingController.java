package org.zerogravitysolutions.training;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    @GetMapping(path = "/trainings")
    public ResponseEntity<List<TrainingDto>> findAll(){
        return trainingService.findAll();
    }

    @GetMapping(path = "/trainings/{id}")
    public ResponseEntity<TrainingDto> findById(@PathVariable Long id){
        return trainingService.findById(id);
    }

    @PutMapping(path = "/trainings/{id}")
    public ResponseEntity<TrainingDto> update(@RequestBody TrainingDto trainingDto){
        return trainingService.update(trainingDto);
    }

    @PostMapping(path = "/trainings/{id}/cover")
    public ResponseEntity<TrainingDto> uploadCover(@PathVariable Long id, @RequestParam(name = "file")  MultipartFile file){
        return trainingService.uploadCoverOnStorage(id, file);
    }

    @GetMapping(path = "/trainings/{id}/cover")
    public ResponseEntity<Resource> readCover(@PathVariable Long id){
        return trainingService.readCover(id);
    }
}
