package org.zerogravitysolutions.instructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InstructorController {

    private final InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @PostMapping(path = "/instructors")
    public ResponseEntity<InstructorDto> save(@RequestBody InstructorDto instructorDto){
        return instructorService.save(instructorDto);
    }

    @GetMapping(path = "/instructors")
    public ResponseEntity<List<InstructorDto>> findAll(){
        return instructorService.findAll();
    }

    @GetMapping(path = "/instructors/{id}")
    public ResponseEntity<InstructorDto> findById(@PathVariable Long id){
        return instructorService.findById(id);
    }

    @PutMapping(path = "/instructors/{id}")
    public ResponseEntity<InstructorDto> update(@RequestBody InstructorDto instructorDto){
        return instructorService.update(instructorDto);
    }

    @PatchMapping(path = "/instructors/{id}")
    public ResponseEntity<InstructorDto> partialUpdate(@PathVariable Long id, @RequestBody InstructorDto instructorDto){
        return instructorService.partialUpdate(id, instructorDto);
    }
}
