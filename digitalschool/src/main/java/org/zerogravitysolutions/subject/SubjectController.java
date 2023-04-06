package org.zerogravitysolutions.subject;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping(path = "/subjects")
    public ResponseEntity<SubjectDto> save(@RequestBody SubjectDto subjectDto){
        return subjectService.save(subjectDto);
    }

    @GetMapping(path = "/subjects")
    public ResponseEntity<List<SubjectDto>> findAll(){
        return subjectService.findAll();
    }

    @GetMapping(path = "/subjects/{id}")
    public ResponseEntity<SubjectDto> findById(@PathVariable Long id){
        return subjectService.findById(id);
    }

    @PutMapping(path = "/subjects/{id}")
    public ResponseEntity<SubjectDto> update(@RequestBody SubjectDto subjectDto){
        return subjectService.update(subjectDto);
    }
}
