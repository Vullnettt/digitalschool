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

    @PostMapping(path = "/v1/instructors")
    @Operation(
            description = "Save instructor by providing instructor entity fields in request body, " +
                    "relationships between instructor and other entities are optional",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The Instructor Saved Successfully",
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
                                                    value = "{\"code\" : 400, \"Status\" : \"Bad Request!\", \"Message\" : \"Bad Request!\"}"
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
    public ResponseEntity<InstructorDto> save(@RequestBody InstructorDto instructorDto){
        return instructorService.save(instructorDto);
    }

    @GetMapping(path = "/v1/instructors")
    @Operation(
            description = "Find all instructors",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The Instructors Were All Found Successfully",
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
                                                    value = "{\"code\" : 400, \"Status\" : \"Bad Request!\", \"Message\" : \"Bad Request!\"}"
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
    public ResponseEntity<List<InstructorDto>> findAll(){
        return instructorService.findAll();
    }

    @GetMapping(path = "/v1/instructors/{id}")
    @Operation(
            description = "Find Instructor by providing id in path variable",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The Instructor Found By Id Successfully",
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
                                                    value = "{\"code\" : 400, \"Status\" : \"Bad Request!\", \"Message\" : \"Bad Request!\"}"
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
    public ResponseEntity<InstructorDto> findById(@PathVariable Long id){
        return instructorService.findById(id);
    }

    @PutMapping(path = "/v1/instructors/{id}")
    @Operation(
            description = "Update instructor by providing fields of instructor entity in request body and id in path variable",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The Instructor Updated Successfully",
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
                                                    value = "{\"code\" : 400, \"Status\" : \"Bad Request!\", \"Message\" : \"Bad Request!\"}"
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
    public ResponseEntity<InstructorDto> update(@RequestBody InstructorDto instructorDto){
        return instructorService.update(instructorDto);
    }

    @PatchMapping(path = "/v1/instructors/{id}")
    public ResponseEntity<InstructorDto> partialUpdate(@PathVariable Long id, @RequestBody InstructorDto instructorDto){
        return instructorService.partialUpdate(id, instructorDto);
    }

    @PutMapping(path = "/v1/instructors/{id}/disable")
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
