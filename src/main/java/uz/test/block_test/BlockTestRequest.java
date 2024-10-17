package uz.test.block_test;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class BlockTestRequest {
    @NotBlank
    private String name;

    @NotNull
    private UUID subjectId;
}
