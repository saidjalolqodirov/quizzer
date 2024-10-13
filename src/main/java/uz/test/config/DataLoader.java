package uz.test.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import uz.test.constants.RoleEnum;
import uz.test.users.UserEntity;
import uz.test.users.UserRepository;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            UserEntity user = new UserEntity();
            user.setUsername("admin");
            user.setPassword(bCryptPasswordEncoder.encode("123456"));
            user.setRole(RoleEnum.ADMIN);
            userRepository.save(user);
        }
    }
}
