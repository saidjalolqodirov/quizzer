package uz.test.users;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import uz.test.config.TokenUtil;
import uz.test.config.UserDetailsImpl;
import uz.test.users.dto.AuthResponse;
import uz.test.users.dto.SignRequestDto;

import java.util.UUID;

@Service
public class UserService {
    private final UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final TokenUtil tokenUtil;
    @Value("${app.jjwt.access_expiration}")
    private String accessExpirationTime;
    @Value("${app.jjwt.refresh_expiration}")
    private String refreshExpirationTime;

    public UserService(UserRepository repository, @Lazy BCryptPasswordEncoder passwordEncoder, TokenUtil tokenUtil) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.tokenUtil = tokenUtil;
    }

    public UserEntity findById(UUID userId) {
        return repository.findById(userId).orElse(null);
    }

    public AuthResponse sign(SignRequestDto requestDto) {
        UserEntity userEntity = repository.findByUsernameAndDeletedFalse(requestDto.getUsername());
        if (userEntity != null) {
            if (!passwordEncoder.matches(requestDto.getPassword(), userEntity.getPassword())) {
                throw new BadCredentialsException(requestDto.getUsername());
            }
        } else {
            throw new BadCredentialsException(requestDto.getUsername());
        }
        return createAuthResponse(userEntity);
    }

    private AuthResponse createAuthResponse(UserEntity userEntity) {
        UserDetailsImpl userDetails = generateUserDetails(userEntity);
        String accessToken = tokenUtil.generateAccessToken(userDetails);
        String refreshToken = tokenUtil.generateRefreshToken(userDetails);
        return new AuthResponse(accessToken, refreshToken, userEntity.getId().toString(), userEntity.getRole().name(), Long.parseLong(accessExpirationTime), Long.parseLong(refreshExpirationTime));
    }

    private UserDetailsImpl generateUserDetails(UserEntity userEntity) {
        return new UserDetailsImpl(userEntity);
    }
}
