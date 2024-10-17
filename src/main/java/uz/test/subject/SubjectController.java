package uz.test.subject;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.test.constants.ProjectUrls;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@RestController
@Tag(name = "Subject", description = "Fanlar")
public class SubjectController {
    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping(ProjectUrls.API_V1_SUBJECT_CREATE)
    @Operation(security = {@SecurityRequirement(name = "bearerAuth")})
    public ResponseEntity<SubjectResponseDto> create(
            @Valid @RequestBody SubjectRequest request
    ) {
        SubjectEntity subject = subjectService.create(request);
        return ResponseEntity.ok(new SubjectResponseDto(subject));
    }

    @PatchMapping(ProjectUrls.API_V1_SUBJECT_UPDATE)
    @Operation(security = {@SecurityRequirement(name = "bearerAuth")})
    public ResponseEntity<SubjectResponseDto> update(
            @RequestParam @NotBlank String id,
            @Valid @RequestBody SubjectRequest request) {
        SubjectEntity subject = subjectService.update(id, request);
        return ResponseEntity.ok(new SubjectResponseDto(subject));
    }

    @GetMapping(ProjectUrls.API_V1_SUBJECT_GET)
    @Operation(security = {@SecurityRequirement(name = "bearerAuth")})
    public ResponseEntity<SubjectResponseDto> get(
            @RequestParam @NotBlank String id) {
        SubjectEntity subject = subjectService.findById(id);
        return ResponseEntity.ok(new SubjectResponseDto(subject));
    }

    @GetMapping(ProjectUrls.API_V1_SUBJECT_LIST)
    @Operation(security = {@SecurityRequirement(name = "bearerAuth")})
    public ResponseEntity<List<SubjectResponseDto>> list() {
        List<SubjectEntity> list = subjectService.list();
        List<SubjectResponseDto> response = new ArrayList<>();
        list.forEach(subjectEntity -> response.add(new SubjectResponseDto(subjectEntity)));
        return ResponseEntity.ok(response);
    }


    @DeleteMapping(ProjectUrls.API_V1_SUBJECT_DELETE)
    @Operation(security = {@SecurityRequirement(name = "bearerAuth")})
    public ResponseEntity<Void> delete(
            @RequestParam @NotBlank String id) {
        subjectService.delete(id);
        return ResponseEntity.ok(null);
    }
}
