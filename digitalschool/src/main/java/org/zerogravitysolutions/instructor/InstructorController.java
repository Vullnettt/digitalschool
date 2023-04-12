package org.zerogravitysolutions.instructor;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerogravitysolutions.instructor.disable_reason.DisableReason;

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

    @PutMapping(path = "/instructors/{id}/disable")
    @Operation(
            description = "Disable Instructor by providing instructor id as path variable and provide disable reason as string in request body",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Instructor Disabled Successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = InstructorDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples ={
                                            @ExampleObject(
                                                    value = "{\"code\" : 400, \"Status\" : \"Bad Request!\", \"Message\" : \"Instructor not found!\"}"
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples ={
                                            @ExampleObject(
                                                    value = "{\"code\" : 500, \"Status\" : \"Internal Server Error!\", \"Message\" : \"Internal Server Error!\"}"
                                            )
                                    }
                            )
                    )
            }
    )
    public ResponseEntity<InstructorDto> disable(@PathVariable Long id, @RequestBody DisableReason disableReason){
        return instructorService.disable(id, disableReason);
    }
}
