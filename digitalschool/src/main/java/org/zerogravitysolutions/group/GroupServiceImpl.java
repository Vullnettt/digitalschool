package org.zerogravitysolutions.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.zerogravitysolutions.group.utils.GroupMapper;
import org.zerogravitysolutions.training.TrainingDto;
import org.zerogravitysolutions.training.TrainingEntity;
import org.zerogravitysolutions.training.TrainingService;
import org.zerogravitysolutions.training.utils.TrainingMapper;

import java.sql.Timestamp;

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
}
