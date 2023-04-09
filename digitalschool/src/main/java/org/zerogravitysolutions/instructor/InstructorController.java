package org.zerogravitysolutions.instructor;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class InstructorController {

    private final InstructorRepository instructorRepository;

    public InstructorController(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }


}
