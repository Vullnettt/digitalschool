package org.zerogravitysolutions.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.zerogravitysolutions.group.utils.GroupMapper;
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

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository, GroupMapper groupMapper, TrainingService trainingService, TrainingMapper trainingMapper) {
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
        this.trainingService = trainingService;
        this.trainingMapper = trainingMapper;
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
}
