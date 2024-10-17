package uz.test.block_test;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.test.constants.ProjectUrls;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class BlockTestController {
    private final BlockTestService blockTEstService;

    public BlockTestController(BlockTestService blockTEstService) {
        this.blockTEstService = blockTEstService;
    }

    @PostMapping(ProjectUrls.API_V1_BLOCK_TEST_CREATE)
    @Operation(security = {@SecurityRequirement(name = "bearerAuth")})
    public ResponseEntity<BlockTestResponseDto> create(
            @Valid @RequestBody BlockTestRequest request
    ) {
        BlockTestEntity blockTest = blockTEstService.create(request);
        return ResponseEntity.ok(new BlockTestResponseDto(blockTest));
    }

    @PutMapping(ProjectUrls.API_V1_BLOCK_TEST_UPDATE)
    @Operation(security = {@SecurityRequirement(name = "bearerAuth")})
    public ResponseEntity<BlockTestResponseDto> update(
            @RequestParam UUID id,
            @Valid @RequestBody BlockTestRequest request
    ) {
        BlockTestEntity blockTest = blockTEstService.update(id, request);
        return ResponseEntity.ok(new BlockTestResponseDto(blockTest));
    }

    @GetMapping(ProjectUrls.API_V1_BLOCK_TEST_GET)
    @Operation(security = {@SecurityRequirement(name = "bearerAuth")})
    public ResponseEntity<BlockTestResponseDto> getOne(
            @RequestParam UUID id
    ) {
        BlockTestEntity blockTest = blockTEstService.getOne(id);
        return ResponseEntity.ok(new BlockTestResponseDto(blockTest));
    }

    @GetMapping(ProjectUrls.API_V1_BLOCK_TEST_LIST)
    @Operation(security = {@SecurityRequirement(name = "bearerAuth")})
    public ResponseEntity<List<BlockTestResponseDto>> list() {
        List<BlockTestEntity> blockTest = blockTEstService.list();
        List<BlockTestResponseDto> response = new ArrayList<>();
        blockTest.forEach(blockTestEntity -> response.add(new BlockTestResponseDto(blockTestEntity)));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(ProjectUrls.API_V1_BLOCK_TEST_DELETE)
    @Operation(security = {@SecurityRequirement(name = "bearerAuth")})
    public void delete(
            @RequestParam UUID id
    ) {
        blockTEstService.delete(id);
    }
}
