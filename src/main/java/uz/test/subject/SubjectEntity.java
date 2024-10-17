package uz.test.subject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import uz.test.generic.GenericAuditingEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "subjects", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "update subjects set deleted = 'true' where id = ?")
@Where(clause = "deleted = 'false'")
public class SubjectEntity extends GenericAuditingEntity {

    @Column(name = "name", unique = true, nullable = false)
    private String name;
}
