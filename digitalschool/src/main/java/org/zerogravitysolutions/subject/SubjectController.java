package org.zerogravitysolutions.subject;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping(path = "/v1/subjects")
    @Operation(
            description = "Save subject by providing fields of entity in body",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The Subject Saved Successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = SubjectDto.class)
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
    public ResponseEntity<SubjectDto> save(@RequestBody SubjectDto subjectDto){
        return subjectService.save(subjectDto);
    }

    @GetMapping(path = "/v1/subjects")
    @Operation(
            description = "Find all subjects",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The Subjects Were All Found Successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = SubjectDto.class)
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
    public ResponseEntity<List<SubjectDto>> findAll(){
        return subjectService.findAll();
    }

    @GetMapping(path = "/v1/subjects/{id}")
    @Operation(
            description = "Find subject by providing id in path",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The Subject Found By Id Successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = SubjectDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples ={
                                            @ExampleObject(
                                                    value = "{\"code\" : 400, \"Status\" : \"Bad Request!\", \"Message\" : \"Not Found!\"}"
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
    public ResponseEntity<SubjectDto> findById(@PathVariable Long id){
        return subjectService.findById(id);
    }

    @PutMapping(path = "/v1/subjects/{id}")
    @Operation(
            description = "Update subject by providing fields of entity in body and id in path",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The Subject Update Successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = SubjectDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples ={
                                            @ExampleObject(
                                                    value = "{\"code\" : 400, \"Status\" : \"Bad Request!\", \"Message\" : \"Not Found!\"}"
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
    public ResponseEntity<SubjectDto> update(@RequestBody SubjectDto subjectDto){
        return subjectService.update(subjectDto);
    }
}
