package org.zerogravitysolutions.instructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.zerogravitysolutions.client.EmailFeignClient;
import org.zerogravitysolutions.instructor.disable_reason.DisableReason;
import org.zerogravitysolutions.instructor.disable_reason.DisableReasonRepository;
import org.zerogravitysolutions.instructor.utils.InstructorMapper;

import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional
public class InstructorServiceImpl implements InstructorService{

    private final InstructorRepository instructorRepository;
    private final InstructorMapper instructorMapper;
    private final DisableReasonRepository disableReasonRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(InstructorServiceImpl.class);
    private final EmailFeignClient emailFeignClient;

    @Autowired
    public InstructorServiceImpl(InstructorRepository instructorRepository, InstructorMapper instructorMapper, DisableReasonRepository disableReasonRepository, EmailFeignClient emailFeignClient) {
        this.instructorRepository = instructorRepository;
        this.instructorMapper = instructorMapper;
        this.disableReasonRepository = disableReasonRepository;
        this.emailFeignClient = emailFeignClient;
    }

    @Override
    public ResponseEntity<InstructorDto> save(InstructorDto instructorDto) {
        InstructorEntity instructorEntity = new InstructorEntity();
        instructorEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        instructorEntity.setCreatedBy(1L);
        instructorMapper.mapDtoToEntity(instructorDto, instructorEntity);
        return ResponseEntity.ok().body(instructorMapper.mapsEntityToDto(instructorRepository.save(instructorEntity), instructorDto));
    }

    @Override
    public ResponseEntity<List<InstructorDto>> findAll() {
        List<InstructorEntity> instructorEntities = instructorRepository.findByDeletedAtIsNull();
        return ResponseEntity.ok().body(instructorEntities.stream().map(instructorEntity -> instructorMapper.mapEntityToDto(instructorEntity)).toList());
    }

    @Override
    public ResponseEntity<InstructorDto> findById(Long id) {
        InstructorEntity instructorEntity = instructorRepository.findByIdAndDeletedAtIsNull(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Instructor with id: " + id + " not found"));
        return ResponseEntity.ok().body(instructorMapper.mapEntityToDto(instructorEntity));
    }
    @Override
    public ResponseEntity<InstructorDto> update(InstructorDto instructorDto) {
        InstructorEntity instructorEntity = new InstructorEntity();
        instructorEntity.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        instructorEntity.setUpdatedBy(1L);
        instructorMapper.mapDtoToEntity(instructorDto, instructorEntity);
        return ResponseEntity.ok().body(instructorMapper.mapsEntityToDto(instructorRepository.save(instructorEntity), instructorDto));    }

    @Override
    public ResponseEntity<InstructorDto> partialUpdate(Long id, InstructorDto instructorDto) {
        InstructorEntity instructorEntity = instructorRepository.findByIdAndDeletedAtIsNull(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Instructor with id: " + id + " not found"));

        instructorEntity.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        instructorEntity.setUpdatedBy(1L);

        instructorMapper.mapDtoToEntity(instructorDto, instructorEntity);
        return ResponseEntity.ok().body(instructorMapper.mapsEntityToDto(instructorRepository.save(instructorEntity), instructorDto));
    }

    @Override
    public ResponseEntity<InstructorDto> disable(Long id, DisableReason disableReason) {

        InstructorEntity instructorEntity = instructorRepository.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Instructor with id: " + id + " not found."));
        instructorEntity.setDeletedAt(new Timestamp(System.currentTimeMillis()));
        instructorEntity.setDeletedBy(1L);
        disableReason.setInstructor(instructorEntity);
        disableReason.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        disableReason.setCreatedBy(1L);
        disableReasonRepository.save(disableReason);
        LOGGER.info("Disable instructor with id {}", instructorEntity.getId());

        emailFeignClient.send("[" + '"' + instructorEntity.getEmail() + '"' + "]", "You are disabled from training",
                messageBody(id, disableReason), null, null, null);

        LOGGER.info("Sending email to {}", instructorEntity.getEmail());

        return ResponseEntity.ok().body(instructorMapper.mapEntityToDto(instructorRepository.save(instructorEntity)));
    }

    private String messageBody(Long id, DisableReason disableReason){
        InstructorEntity instructorEntity = instructorRepository.findById(id).get();
        String messageBody = "Dear: " + instructorEntity.getFirstName() +
                "\n\nReason why you are suspended from training as instructor: " +
                "\n" + disableReason.getDisableReason() +
                "\n\n\n" + "Date And Time of suspended from: " + "\nDate: '" + instructorEntity.getDeletedAt().toLocalDateTime().getYear() + "-" +
                instructorEntity.getDeletedAt().toLocalDateTime().getMonth() + "-" + instructorEntity.getDeletedAt().toLocalDateTime().getDayOfMonth() +"'"+
                "\nTime: '" + instructorEntity.getDeletedAt().toLocalDateTime().getHour() + ":" + instructorEntity.getDeletedAt().toLocalDateTime().getMinute() + "'";

        return messageBody;
    }
}
