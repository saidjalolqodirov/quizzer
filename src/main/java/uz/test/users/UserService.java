package uz.test.users;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public UserEntity findById(UUID userId) {
        return repository.findById(userId).orElse(null);
    }
}
