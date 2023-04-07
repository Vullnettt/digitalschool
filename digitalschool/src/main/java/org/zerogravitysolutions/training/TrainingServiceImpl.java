package org.zerogravitysolutions.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.zerogravitysolutions.image_storage.ImageSize;
import org.zerogravitysolutions.image_storage.ImageStorageService;
import org.zerogravitysolutions.training.utils.TrainingMapper;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@Service
public class TrainingServiceImpl implements TrainingService {

    private final TrainingRepository trainingRepository;
    private final TrainingMapper trainingMapper;
    private final ImageStorageService imageStorageService;

    @Autowired
    public TrainingServiceImpl(TrainingRepository trainingRepository, TrainingMapper trainingMapper, ImageStorageService imageStorageService) {
        this.trainingRepository = trainingRepository;
        this.trainingMapper = trainingMapper;
        this.imageStorageService = imageStorageService;
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

    @Override
    public ResponseEntity<TrainingDto> uploadCover(Long id, MultipartFile file) {
        TrainingEntity trainingEntity = trainingRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Training with id: " + id + " not found."));

        try{
            byte[] coverImage = file.getBytes();
            trainingEntity.setCoverImage(coverImage);

            trainingEntity.setUpdatedBy(1L);
            trainingEntity.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            trainingRepository.save(trainingEntity);
            return ResponseEntity.ok().body(trainingMapper.mapEntityToDto(trainingEntity));
        } catch (IOException e) {

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to upload training cover image.");
        }
    }

    @Override
    public ResponseEntity<Resource> readCover(Long id) {
        TrainingEntity trainingEntity = trainingRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Training with id: " + id + " not found."));

        if(trainingEntity.getCoverImage() != null){
            ByteArrayResource resource = new ByteArrayResource(trainingEntity.getCoverImage());

            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .header(HttpHeaders.CONTENT_DISPOSITION)
                    .body(resource);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Failed to load training cover image for training id: " + id);
        }
    }

    @Override
    public ResponseEntity<TrainingDto> uploadCoverOnStorage(Long id, MultipartFile file) {
        TrainingEntity trainingEntity = trainingRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Training with id: " + id + " not found."));

        String fileName = imageStorageService.saveImage(file, trainingEntity.getCoverImageFileName());

        trainingEntity.setCoverImageFileName(fileName);
        trainingEntity.setUpdatedBy(1L);
        trainingEntity.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        trainingRepository.save(trainingEntity);
        trainingEntity.setInstructors(null);

        return ResponseEntity.ok().body(trainingMapper.mapEntityToDto(trainingEntity));
    }

    @Override
    public ResponseEntity<Resource> readCoverFromStorage(Long id, ImageSize imageSize) {
        TrainingEntity trainingEntity = trainingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Training with id: " + id + " not found."));

        String coverImageFileName = trainingEntity.getCoverImageFileName();

        Resource resource = imageStorageService.loadImage(coverImageFileName, imageSize);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }
}
