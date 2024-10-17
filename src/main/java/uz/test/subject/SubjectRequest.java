package uz.test.subject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectRequest {
    @NotBlank(message = "value_is_blank")
    private String name;
}
