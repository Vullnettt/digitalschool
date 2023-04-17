package org.zerogravitysolutions.student;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.zerogravitysolutions.client.EmailFeignClient;
import org.zerogravitysolutions.group.student_groups.StudentGroupRepository;
import org.zerogravitysolutions.student.utils.StudentMapper;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final StudentGroupRepository studentGroupRepository;
    private final EmailFeignClient emailFeignClient;

    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper, StudentGroupRepository studentGroupRepository, EmailFeignClient emailFeignClient) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.studentGroupRepository = studentGroupRepository;
        this.emailFeignClient = emailFeignClient;
    }

    @Override
    public ResponseEntity<StudentDto> save(StudentDto studentDto) {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        studentEntity.setCreatedBy(1L);
        studentMapper.mapDtoToEntity(studentDto, studentEntity);
        return ResponseEntity.ok().body(studentMapper.mapEntityToDto(studentRepository.save(studentEntity)));
    }

    @Override
    public ResponseEntity<List<StudentDto>> findAll() {
        List<StudentEntity> studentEntities = studentRepository.findByDeletedAtIsNull();
        return ResponseEntity.ok().body(studentEntities.stream().map(studentEntity -> studentMapper.mapEntityToDto(studentEntity)).toList());
    }

    @Override
    public ResponseEntity<StudentDto> findById(Long id) {
        StudentEntity studentEntity = studentRepository.findByIdAndDeletedAtIsNull(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Student with id: " + id + " not found"));
        return ResponseEntity.ok().body(studentMapper.mapEntityToDto(studentEntity));
    }

    @Override
    public ResponseEntity<StudentDto> update(StudentDto studentDto) {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        studentEntity.setUpdatedBy(1L);
        studentMapper.mapDtoToEntity(studentDto, studentEntity);
        return ResponseEntity.ok().body(studentMapper.mapEntityToDto(studentRepository.save(studentEntity)));
    }

    @Override
    @Transactional
    public ResponseEntity<StudentDto> disable(Long id) {
        StudentEntity studentEntity = studentRepository.findByIdAndDeletedAtIsNull(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Student with id: " + id + " not found"));
        studentEntity.setDeletedAt(new Timestamp(System.currentTimeMillis()));
        studentEntity.setDeletedBy(1L);
        studentGroupRepository.deleteByStudentId(id);

        emailFeignClient.send("[" + '"' + studentEntity.getEmail() + '"' + "]", "You are disabled from training",
                messageBodyDisable(id), null, null, null);
        return ResponseEntity.ok().body(studentMapper.mapEntityToDto(studentRepository.save(studentEntity)));
    }

    private String messageBodyDisable(Long id){
        StudentEntity studentEntity = studentRepository.findById(id).get();
        String messageBody = "Dear: " + studentEntity.getFirstName() +
                "\n\nYou are a deactivated student, you are removed from all groups, you cannot apply to groups until you are activated by admin\n " +
                "\n\n\n" + "Date And Time of deactivation: " + "\nDate: '" + studentEntity.getDeletedAt().toLocalDateTime().getYear() + "-" +
                studentEntity.getDeletedAt().toLocalDateTime().getMonth() + "-" + studentEntity.getDeletedAt().toLocalDateTime().getDayOfMonth() +"'"+
                "\nTime: '" + studentEntity.getDeletedAt().toLocalDateTime().getHour() + ":" + studentEntity.getDeletedAt().toLocalDateTime().getMinute() + "'";

        return messageBody;
    }

    @Override
    public ResponseEntity<?> enable(Long id) {
        StudentEntity studentEntity = studentRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Student with id: " + id + " not found"));
        if(studentEntity.getDeletedAt() == null || studentEntity.getDeletedBy() == null){
            return ResponseEntity.ok().body("Email is already enabled!");
        }
        emailFeignClient.send("[" + '"' + studentEntity.getEmail() + '"' + "]", "You are disabled from training",
                messageBodyEnable(id), null, null, null);

        studentEntity.setDeletedAt(null);
        studentEntity.setDeletedBy(null);

        return ResponseEntity.ok().body(studentMapper.mapEntityToDto(studentRepository.save(studentEntity)));
    }

    private String messageBodyEnable(Long id){
        StudentEntity studentEntity = studentRepository.findById(id).get();
        Long hour = Long.valueOf(LocalDateTime.now().getHour() - studentEntity.getDeletedAt().toLocalDateTime().getHour());;
        Long minute = Long.valueOf(LocalDateTime.now().getMinute() - studentEntity.getDeletedAt().toLocalDateTime().getMinute());
        Long second = Long.valueOf(LocalDateTime.now().getSecond() - studentEntity.getDeletedAt().toLocalDateTime().getSecond());;
        String now = String.valueOf("Date: " + LocalDateTime.now().getYear() + "-" + LocalDateTime.now().getMonth() + "-" + LocalDateTime.now().getDayOfMonth() +
                "  Time: " + LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute() + ":" + LocalDateTime.now().getSecond());
        String messageBody = "Dear: " + studentEntity.getFirstName() +
                "\n\nYou are a enabled student\n " +
                "\nEnabled in:" + now +
                "\nYou was disabled for: " + hour + ":" + minute + ":" + second;

        return messageBody;
    }
}
