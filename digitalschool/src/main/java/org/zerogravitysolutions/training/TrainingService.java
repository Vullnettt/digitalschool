package org.zerogravitysolutions.training;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.zerogravitysolutions.image_storage.ImageSize;

import java.util.List;

public interface TrainingService {
    ResponseEntity<TrainingDto> save(TrainingDto trainingDto);

    ResponseEntity<List<TrainingDto>> findAll();

    ResponseEntity<TrainingDto> findById(Long id);

    ResponseEntity<TrainingDto> update(TrainingDto trainingDto);

    ResponseEntity<TrainingDto> uploadCover(Long id, MultipartFile file);

    ResponseEntity<Resource> readCover(Long id);

    ResponseEntity<TrainingDto> uploadCoverOnStorage(Long id, MultipartFile file);

    ResponseEntity<Resource> readCoverFromStorage(Long id, ImageSize imageSize);

    ResponseEntity<TrainingDto> partialUpdate(Long id, TrainingDto trainingDto);

    ResponseEntity<TrainingDto> addInstructor(Long trainingId, Long instructorId);

    Page<TrainingDto> findAllPageable(Pageable pageable);
}
