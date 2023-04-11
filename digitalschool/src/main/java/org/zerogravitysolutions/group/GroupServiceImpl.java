package org.zerogravitysolutions.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.zerogravitysolutions.group.group_intructors.GroupInstructor;
import org.zerogravitysolutions.group.group_intructors.GroupInstructorRepository;
import org.zerogravitysolutions.group.student_groups.StudentGroup;
import org.zerogravitysolutions.group.student_groups.StudentGroupRepository;
import org.zerogravitysolutions.group.utils.GroupMapper;
import org.zerogravitysolutions.instructor.InstructorDto;
import org.zerogravitysolutions.instructor.InstructorEntity;
import org.zerogravitysolutions.instructor.InstructorService;
import org.zerogravitysolutions.instructor.utils.InstructorMapper;
import org.zerogravitysolutions.student.StudentDto;
import org.zerogravitysolutions.student.StudentEntity;
import org.zerogravitysolutions.student.StudentService;
import org.zerogravitysolutions.student.utils.StudentMapper;
import org.zerogravitysolutions.training.TrainingDto;
import org.zerogravitysolutions.training.TrainingEntity;
import org.zerogravitysolutions.training.TrainingService;
import org.zerogravitysolutions.training.utils.TrainingMapper;

import java.sql.Timestamp;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;
    private final TrainingService trainingService;
    private final TrainingMapper trainingMapper;
    private final StudentService studentService;
    private final StudentMapper studentMapper;
    private final StudentGroupRepository studentGroupRepository;
    private final InstructorService instructorService;
    private final InstructorMapper instructorMapper;
    private final GroupInstructorRepository groupInstructorRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository, GroupMapper groupMapper, TrainingService trainingService, TrainingMapper trainingMapper, StudentService studentService, StudentMapper studentMapper, StudentGroupRepository studentGroupRepository, InstructorService instructorService, InstructorMapper instructorMapper, GroupInstructorRepository groupInstructorRepository) {
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
        this.trainingService = trainingService;
        this.trainingMapper = trainingMapper;
        this.studentService = studentService;
        this.studentMapper = studentMapper;
        this.studentGroupRepository = studentGroupRepository;
        this.instructorService = instructorService;
        this.instructorMapper = instructorMapper;
        this.groupInstructorRepository = groupInstructorRepository;
    }

    @Override
    public ResponseEntity<GroupDto> save(GroupDto groupDto) {
        GroupEntity groupEntity = new GroupEntity();
        TrainingEntity trainingEntity = new TrainingEntity();
        TrainingDto trainingDto = trainingService.findById(groupDto.getTrainingId()).getBody();
        groupEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        groupEntity.setCreatedBy(1L);
        groupEntity.setTraining(trainingMapper.mapDtoToEntityWithEntityReturnType(trainingDto, trainingEntity));
        groupMapper.mapDtoToEntity(groupDto, groupEntity);
        return ResponseEntity.ok().body(groupMapper.mapsEntityToDto(groupRepository.save(groupEntity), groupDto));
    }

    @Override
    public ResponseEntity<List<GroupDto>> findAll() {
        List<GroupEntity> groupEntities = groupRepository.findByDeletedAtIsNull();
        return ResponseEntity.ok().body(groupEntities.stream().map(groupEntity -> groupMapper.mapEntityToDto(groupEntity)).toList());
    }

    @Override
    public ResponseEntity<GroupDto> findById(Long id) {
        GroupEntity groupEntity = groupRepository.findByIdAndDeletedAtIsNull(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Group with id: " + id + " not found"));
        return ResponseEntity.ok().body(groupMapper.mapEntityToDto(groupEntity));
    }

    @Override
    public ResponseEntity<GroupDto> update(GroupDto groupDto) {
        GroupEntity groupEntity = new GroupEntity();
        TrainingEntity trainingEntity = new TrainingEntity();
        TrainingDto trainingDto = trainingService.findById(groupDto.getTrainingId()).getBody();
        groupEntity.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        groupEntity.setUpdatedBy(1L);
        groupEntity.setTraining(trainingMapper.mapDtoToEntityWithEntityReturnType(trainingDto, trainingEntity));
        groupMapper.mapDtoToEntity(groupDto, groupEntity);
        return ResponseEntity.ok().body(groupMapper.mapsEntityToDto(groupRepository.save(groupEntity), groupDto));

    }

    @Override
    public ResponseEntity<GroupDto> partialUpdate(Long id, GroupDto groupDto) {
        GroupEntity groupEntity = groupRepository.findByIdAndDeletedAtIsNull(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Group with id: " + id + " not found"));
        TrainingEntity trainingEntity = new TrainingEntity();
        TrainingDto trainingDto = trainingService.findById(groupDto.getTrainingId()).getBody();
        groupEntity.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        groupEntity.setUpdatedBy(1L);
        groupEntity.setTraining(trainingMapper.mapDtoToEntityWithEntityReturnType(trainingDto, trainingEntity));
        groupMapper.mapDtoToEntity(groupDto, groupEntity);
        return ResponseEntity.ok().body(groupMapper.mapsEntityToDto(groupRepository.save(groupEntity), groupDto));
    }

    @Override
    public ResponseEntity<GroupDto> addStudent(Long groupId, Long studentId) {
        GroupDto groupDto = new GroupDto();
        StudentEntity studentEntity = new StudentEntity();
        GroupEntity groupEntity = groupRepository.findByIdAndDeletedAtIsNull(groupId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Group with id: " + groupId + " not found"));
        StudentDto studentDto = studentService.findById(studentId).getBody();

        StudentGroup studentGroup = new StudentGroup();
        studentGroup.setGroup(groupEntity);
        studentGroup.setStudent(studentMapper.mapDtoToEntityWithEntityReturnType(studentDto, studentEntity));
        studentGroup.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        studentGroup.setCreatedBy(1L);
        studentGroup.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        studentGroup.setUpdatedBy(1L);
        studentGroupRepository.save(studentGroup);

        groupMapper.mapsEntityToDto(groupEntity, groupDto);
        return ResponseEntity.ok().body(groupDto);
    }

    @Override
    public ResponseEntity<GroupDto> addInstructor(Long groupId, Long instructorId) {
        GroupDto groupDto = new GroupDto();
        InstructorEntity instructorEntity = new InstructorEntity();
        GroupEntity groupEntity = groupRepository.findByIdAndDeletedAtIsNull(groupId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Group with id: " + groupId + " not found"));
        InstructorDto instructorDto = instructorService.findById(instructorId).getBody();

        GroupInstructor groupInstructor = new GroupInstructor();
        groupInstructor.setGroup(groupEntity);
        groupInstructor.setInstructor(instructorMapper.mapDtoToEntityWithEntityReturnType(instructorDto, instructorEntity));
        groupInstructor.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        groupInstructor.setCreatedBy(1L);
        groupInstructor.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        groupInstructor.setUpdatedBy(1L);
        groupInstructorRepository.save(groupInstructor);

        groupMapper.mapsEntityToDto(groupEntity, groupDto);
        return ResponseEntity.ok().body(groupDto);
    }
}
