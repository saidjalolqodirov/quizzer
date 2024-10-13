package uz.test.users;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uz.test.constants.ProjectUrls;
import uz.test.users.dto.AuthResponse;
import uz.test.users.dto.SignRequestDto;

@RestController
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(ProjectUrls.API_V1_AUTH_SIGN)
    public ResponseEntity<AuthResponse> sign(@RequestBody SignRequestDto requestDto) {
        return ResponseEntity.ok(userService.sign(requestDto));
    }
}
