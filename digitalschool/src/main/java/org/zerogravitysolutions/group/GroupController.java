package org.zerogravitysolutions.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping(path = "/groups")
    public ResponseEntity<GroupDto> save(@RequestBody GroupDto groupDto){
        return groupService.save(groupDto);
    }

    @GetMapping(path = "/groups")
    public ResponseEntity<List<GroupDto>> findAll(){
        return groupService.findAll();
    }

    @GetMapping(path = "/groups/{id}")
    public ResponseEntity<GroupDto> findById(@PathVariable Long id){
        return groupService.findById(id);
    }

    @PutMapping(path = "/groups/{id}")
    public ResponseEntity<GroupDto> update(@RequestBody GroupDto groupDto){
        return groupService.update(groupDto);
    }

    @PatchMapping(path = "/groups/{id}")
    public ResponseEntity<GroupDto> update(@PathVariable Long id, @RequestBody GroupDto groupDto){
        return groupService.partialUpdate(id, groupDto);
    }

    @PutMapping(path = "/groups/{groupId}/students/{studentId}")
    public ResponseEntity<GroupDto> addStudent(@PathVariable Long groupId, @PathVariable Long studentId){
        return groupService.addStudent(groupId, studentId);
    }
}
