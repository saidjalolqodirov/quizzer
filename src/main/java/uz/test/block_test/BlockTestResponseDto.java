package uz.test.block_test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlockTestResponseDto {
    private UUID id;
    private String name;
    private UUID subjectId;

    public BlockTestResponseDto(BlockTestEntity blockTest) {
        this.id = blockTest.getId();
        this.name = blockTest.getName();
        this.subjectId = blockTest.getSubjectId();
    }
}
