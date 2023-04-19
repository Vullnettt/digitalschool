package org.zerogravitysolutions.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.annotation.Reference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.zerogravitysolutions.client.EmailFeignClient;
import org.zerogravitysolutions.group.GroupEntity;
import org.zerogravitysolutions.group.GroupService;
import org.zerogravitysolutions.image_storage.ImageSize;
import org.zerogravitysolutions.image_storage.ImageStorageService;
import org.zerogravitysolutions.instructor.InstructorDto;
import org.zerogravitysolutions.instructor.InstructorEntity;
import org.zerogravitysolutions.instructor.InstructorService;
import org.zerogravitysolutions.instructor.utils.InstructorMapper;
import org.zerogravitysolutions.student.StudentService;
import org.zerogravitysolutions.training.training_instructors.TrainingInstructor;
import org.zerogravitysolutions.training.training_instructors.TrainingInstructorRepository;
import org.zerogravitysolutions.training.utils.TrainingMapper;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

@Service
@Transactional
public class TrainingServiceImpl implements TrainingService {

    private final TrainingRepository trainingRepository;
    private final TrainingMapper trainingMapper;
    private final ImageStorageService imageStorageService;
    private final InstructorService instructorService;
    private final InstructorMapper instructorMapper;
    private final TrainingInstructorRepository trainingInstructorRepository;
    private final EmailFeignClient emailFeignClient;
    private final StudentService studentService;
    @Reference(value = GroupService.class)
    private final GroupService groupService;

    @Autowired
    public TrainingServiceImpl(TrainingRepository trainingRepository, TrainingMapper trainingMapper, ImageStorageService imageStorageService, InstructorService instructorService, InstructorMapper instructorMapper,
                               TrainingInstructorRepository trainingInstructorRepository, EmailFeignClient emailFeignClient, StudentService studentService, @Lazy GroupService groupService) {
        this.trainingRepository = trainingRepository;
        this.trainingMapper = trainingMapper;
        this.imageStorageService = imageStorageService;
        this.instructorService = instructorService;
        this.instructorMapper = instructorMapper;
        this.trainingInstructorRepository = trainingInstructorRepository;
        this.emailFeignClient = emailFeignClient;
        this.studentService = studentService;
        this.groupService = groupService;
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
        List<TrainingEntity> trainingEntities = trainingRepository.findByDeletedAtIsNull();
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

    @Override
    public ResponseEntity<TrainingDto> partialUpdate(Long id, TrainingDto trainingDto) {
        TrainingEntity trainingEntity = trainingRepository.findByIdAndDeletedAtIsNull(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Training with id: " + id + " not found"));
        trainingMapper.mapDtoToEntity(trainingDto, trainingEntity);
        trainingEntity.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        trainingEntity.setUpdatedBy(1L);
        return ResponseEntity.ok().body(trainingMapper.mapsEntityToDto(trainingRepository.save(trainingEntity), trainingDto));
    }

    @Override
    public ResponseEntity<TrainingDto> addInstructor(Long trainingId, Long instructorId) {
        TrainingDto trainingDto = new TrainingDto();
        TrainingEntity trainingEntity = trainingRepository.findByIdAndDeletedAtIsNull(trainingId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Training with id: " + trainingId + " not found"));
        InstructorEntity instructorEntity = new InstructorEntity();
        InstructorDto instructorDto = instructorService.findById(instructorId).getBody();
        TrainingInstructor trainingInstructor = new TrainingInstructor();
        trainingInstructor.setTraining(trainingEntity);
        trainingInstructor.setInstructor(instructorMapper.mapDtoToEntityWithEntityReturnType(instructorDto, instructorEntity));
        trainingInstructorRepository.save(trainingInstructor);
        trainingMapper.mapEntityToDto(trainingEntity, trainingDto);

        emailFeignClient.send("[" + '"' + instructorEntity.getEmail() + '"' + "]",
                "You are assign in training: " + trainingEntity.getTitle() + " successfully",
                " ", null, null, null);

        return ResponseEntity.ok().body(trainingDto);
    }

    @Override
    public Page<TrainingDto> findAllPageable(Pageable pageable) {
        Page<TrainingEntity> trainingEntityPage = trainingRepository.findAllByDeletedAtIsNull(pageable);

        List<TrainingEntity> trainingEntities = trainingEntityPage.getContent();
        List<TrainingDto> trainingDtos = new ArrayList<>();

        trainingEntities.forEach(trainingEntity -> {
            Set<GroupEntity> groupEntities = trainingEntity.getGroups();
            long studentCount = studentService.countByGroupsIn(groupEntities);

            TrainingDto trainingDto = new TrainingDto();
            trainingMapper.mapEntityToDto(trainingEntity, trainingDto);
            trainingDto.setStudentCount(studentCount);

            trainingDtos.add(trainingDto);
        });
        return new PageImpl<>(trainingDtos);
    }

    public static Map<String, Object> convertToResponse(Page<TrainingDto> page) {

        Map<String, Object> response = new HashMap<>();

        response.put("trainings", page.getContent());
        response.put("current-page", page.getNumber());
        response.put("total-items", page.getTotalElements());
        response.put("total-pages", page.getTotalPages());
        response.put("last_page", page.isLast());

        return response;
    }

    @Override
    public ResponseEntity<TrainingDto> disable(Long id) {
        TrainingEntity trainingEntity = trainingRepository.findByIdAndDeletedAtIsNull(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Training with id: " + id + " not found"));
        TrainingDto trainingDto = new TrainingDto();
        trainingEntity.setDeletedAt(new Timestamp(System.currentTimeMillis()));
        trainingEntity.setDeletedBy(1L);

        for(GroupEntity groupEntity : trainingEntity.getGroups()){
            if(groupEntity.getDeletedAt() == null) {
                groupService.disable(groupEntity.getId());
            }
        }

        trainingInstructorRepository.deleteByTrainingId(trainingEntity.getId());
        return ResponseEntity.ok().body(trainingMapper.mapsEntityToDto(trainingRepository.save(trainingEntity), trainingDto));
    }
}
