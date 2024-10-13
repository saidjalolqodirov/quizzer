package uz.test.users;

import lombok.Getter;
import lombok.Setter;
import uz.test.constants.RoleEnum;
import uz.test.generic.GenericAuditingEntity;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity extends GenericAuditingEntity {

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;
}
