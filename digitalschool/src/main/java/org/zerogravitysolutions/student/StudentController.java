package org.zerogravitysolutions.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(path = "/students")
    public ResponseEntity<StudentDto> save(@RequestBody StudentDto studentDto){
        return studentService.save(studentDto);
    }

    @GetMapping(path = "/students")
    public ResponseEntity<List<StudentDto>> findAll(){
        return studentService.findAll();
    }

    @GetMapping(path = "/students/{id}")
    public ResponseEntity<StudentDto> findById(@PathVariable Long id){
        return studentService.findById(id);
    }

    @PutMapping(path = "/students/{id}")
    public ResponseEntity<StudentDto> update(@RequestBody StudentDto studentDto){
        return studentService.update(studentDto);
    }
}
