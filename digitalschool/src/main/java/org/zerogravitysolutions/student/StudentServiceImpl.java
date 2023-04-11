package org.zerogravitysolutions.student;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.zerogravitysolutions.student.utils.StudentMapper;

import java.sql.Timestamp;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
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
}
