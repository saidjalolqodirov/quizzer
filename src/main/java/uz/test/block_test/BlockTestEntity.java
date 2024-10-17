package uz.test.block_test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import uz.test.generic.GenericAuditingEntity;
import uz.test.subject.SubjectEntity;

import javax.persistence.*;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "block_test")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "update block_test set deleted = 'true' where id = ?")
@Where(clause = "deleted = 'false'")
public class BlockTestEntity extends GenericAuditingEntity {

    @ManyToOne
    @JoinColumn(name = "subject_id", insertable = false, updatable = false)
    private SubjectEntity subject;

    @Column(name = "subject_id", nullable = false)
    private UUID subjectId;

    @Column(nullable = false)
    private String name;
}
