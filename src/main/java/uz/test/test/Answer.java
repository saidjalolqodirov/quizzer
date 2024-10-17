package uz.test.test;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Answer {
    @NotBlank
    private String A;
    @NotBlank
    private String B;
    @NotBlank
    private String C;
    @NotBlank
    private String D;
}
