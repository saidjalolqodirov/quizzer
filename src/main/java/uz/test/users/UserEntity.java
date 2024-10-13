package uz.test.users;

import lombok.Getter;
import lombok.Setter;
import uz.test.constants.RoleEnum;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Setter
@Entity
public class UserEntity {
    @Id
    private UUID id;
    private RoleEnum role;
    private String username;
    private String password;
}
