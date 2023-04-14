package org.zerogravitysolutions.training;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.zerogravitysolutions.image_storage.ImageSize;

import java.util.List;

@RestController
public class TrainingController {

    private final TrainingService trainingService;

    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @PostMapping(path = "/v1/trainings")
    @Operation(
            description = "Save training by providing fields of entity in body",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The Training Saved Successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TrainingDto.class)
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
    public ResponseEntity<TrainingDto> save(@RequestBody TrainingDto trainingDto){
        return trainingService.save(trainingDto);
    }

    @GetMapping(path = "/v1/trainings")
    @Operation(
            description = "Find all trainings",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The Training Were All Found Successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TrainingDto.class)
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
    public ResponseEntity<List<TrainingDto>> findAll(){
        return trainingService.findAll();
    }

    @GetMapping(path = "/v1/trainings/{id}")
    @Operation(
            description = "Find training by providing id in path",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The Training Found By Id Successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TrainingDto.class)
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
    public ResponseEntity<TrainingDto> findById(@PathVariable Long id){
        return trainingService.findById(id);
    }

    @PutMapping(path = "/v1/trainings/{id}")
    @Operation(
            description = "Update training by providing fields of entity in body and id in path",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The Training Update Successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TrainingDto.class)
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
    public ResponseEntity<TrainingDto> update(@RequestBody TrainingDto trainingDto){
        return trainingService.update(trainingDto);
    }

    @PostMapping(path = "/v1/trainings/{id}/cover", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            description = "Upload your cover image by providing training with id and select photo in file," +
                    " this cover image will saved in database as byte",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The Cover Image Saved Successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TrainingDto.class)
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
    public ResponseEntity<TrainingDto> uploadCover(@PathVariable Long id, @RequestPart(name = "file")  MultipartFile file){
        return trainingService.uploadCover(id, file);
    }

    @GetMapping(path = "/v1/trainings/{id}/cover")
    @Operation(
            description = "Find cover by providing instructor id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The Cover Image Found Successfully"
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
    public ResponseEntity<Resource> readCover(@PathVariable Long id){
        return trainingService.readCover(id);
    }

    @PostMapping(path = "/v1/trainings/{id}/cover/storage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            description = "Upload your cover image by providing training with id and select photo in file," +
                    " this cover image will saved in file system in four dimensions",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The Cover Image Saved Successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TrainingDto.class)
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
    public ResponseEntity<TrainingDto> uploadCoverOnStorage(@PathVariable Long id, @RequestPart(name = "file")  MultipartFile file){
        return trainingService.uploadCoverOnStorage(id, file);
    }

    @GetMapping(path = "/v1/trainings/{id}/cover/storage", params = "size")
    @Operation(
            description = "Find cover by providing instructor id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The Cover Image Found Successfully"
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
    public ResponseEntity<Resource> readCoverFromStorage(@PathVariable Long id, @RequestParam(name = "size") ImageSize imageSize){
        return trainingService.readCoverFromStorage(id, imageSize);
    }

    @PatchMapping(path = "/v1/trainings/{id}")
    @Operation(
            description = "Partial update training by providing fields of entity in body and id in path",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The Training Update Successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TrainingDto.class)
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
    public ResponseEntity<TrainingDto> partialUpdate(@PathVariable Long id, @RequestBody TrainingDto trainingDto){
        return trainingService.partialUpdate(id, trainingDto);
    }

    @PutMapping(path = "/v1/trainings/{trainingId}/instructors/{instructorId}")
    @Operation(
            description = "Assign instructor in training by providing training id and instructor id in path",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Assign Instructor In Training Successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TrainingDto.class)
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
    public ResponseEntity<TrainingDto> addInstructor(@PathVariable Long trainingId, @PathVariable Long instructorId){
        return trainingService.addInstructor(trainingId, instructorId);
    }
}
