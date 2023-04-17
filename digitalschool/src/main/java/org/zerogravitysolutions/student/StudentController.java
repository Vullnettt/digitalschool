package org.zerogravitysolutions.student;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.zerogravitysolutions.training.TrainingDto;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(path = "/v1/students")
    @Operation(
            description = "Save student by providing fields of entity in body",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The Student Saved Successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = StudentDto.class)
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
    public ResponseEntity<StudentDto> save(@RequestBody StudentDto studentDto){
        return studentService.save(studentDto);
    }

    @GetMapping(path = "/v1/students")
    @Operation(
            description = "Find all student",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The Students Were All Found Successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = StudentDto.class)
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
    public ResponseEntity<List<StudentDto>> findAll(){
        return studentService.findAll();
    }

    @GetMapping(path = "/v1/students/{id}")
    @Operation(
            description = "Find student by providing id in path",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The Student Found By Id Successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = StudentDto.class)
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
    public ResponseEntity<StudentDto> findById(@PathVariable Long id){
        return studentService.findById(id);
    }

    @PutMapping(path = "/students/{id}")
    @Operation(
            description = "Update student by providing fields of entity in body and id in path",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The Student Update Successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = StudentDto.class)
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
    public ResponseEntity<StudentDto> update(@RequestBody StudentDto studentDto){
        return studentService.update(studentDto);
    }

    @PutMapping(path = "/v1/students/{id}/disable")
    @Operation(
            description = "Disable student by providing id in path",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The Student Disabled Successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = StudentDto.class)
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
    public ResponseEntity<StudentDto> disable(@PathVariable Long id){
        return studentService.disable(id);
    }

    @PutMapping(path = "/v1/students/{id}/enable")
    @Operation(
            description = "Enable student by providing id in path",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The Student Enabled Successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = StudentDto.class)
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
    public ResponseEntity<?> enable(@PathVariable Long id){
        return studentService.enable(id);
    }

    @PostMapping(path = "/v1/students/{id}/profile/picture", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            description = "Upload your cover image by providing student id and select photo in file," +
                    " this cover image will saved in database as byte",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The Cover Image Saved Successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = StudentDto.class)
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
    public ResponseEntity<StudentDto> uploadProfilePicture(@PathVariable Long id, @RequestPart(name = "file") MultipartFile file){
        return studentService.uploadProfilePicture(id, file);
    }

    @GetMapping(path = "/v1/students/{id}/profile/picture")
    @Operation(
            description = "Find profile picture by providing student id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The Profile Picture Image Found Successfully",
                            content = @Content(mediaType = "MediaType/IMAGE_JPEG")
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
    public ResponseEntity<Resource> readProfilePicture(@PathVariable Long id){
        return studentService.readProfilePicture(id);
    }

    //upload profile picture and when student is saved enable just with %@gmail.com email
}
