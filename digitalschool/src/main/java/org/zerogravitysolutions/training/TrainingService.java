package org.zerogravitysolutions.training;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TrainingService {
    ResponseEntity<TrainingDto> save(TrainingDto trainingDto);

    ResponseEntity<List<TrainingDto>> findAll();

    ResponseEntity<TrainingDto> findById(Long id);

    ResponseEntity<TrainingDto> update(TrainingDto trainingDto);

    ResponseEntity<TrainingDto> uploadCoverOnStorage(Long id, MultipartFile file);

    ResponseEntity<Resource> readCover(Long id);
}
