package uz.test.users.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthResponse {
    private String accessToken;
    private String refreshToken;
    private String userId;
    private String role;
    private Long accessTokenExpiresIn;
    private Long refreshTokenExpiresIn;
}
