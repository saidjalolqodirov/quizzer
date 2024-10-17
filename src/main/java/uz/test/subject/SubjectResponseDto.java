package uz.test.subject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectResponseDto {
    private UUID id;
    private String name;

    public SubjectResponseDto(SubjectEntity subject) {
        this.id = subject.getId();
        this.name = subject.getName();
    }
}
