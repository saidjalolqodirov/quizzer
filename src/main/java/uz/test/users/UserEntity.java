package uz.test.users;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import uz.test.constants.RoleEnum;
import uz.test.generic.GenericAuditingEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "update users set deleted = 'true' where id = ?")
@Where(clause = "deleted = 'false'")
public class UserEntity extends GenericAuditingEntity {

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;
}
