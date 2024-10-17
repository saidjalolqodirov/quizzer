package uz.test.test;

import com.vladmihalcea.hibernate.type.array.IntArrayType;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;
import uz.test.constants.AnswerQuestion;
import uz.test.constants.TestType;
import uz.test.generic.GenericAuditingEntity;
import uz.test.subject.SubjectEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tests")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "update tests set deleted = 'true' where id = ?")
@Where(clause = "deleted = 'false'")
@TypeDefs({
        @TypeDef(name = "string-array", typeClass = StringArrayType.class),
        @TypeDef(name = "int-array", typeClass = IntArrayType.class),
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class TestEntity extends GenericAuditingEntity {

    @ManyToOne
    @JoinColumn(name = "subject_id", insertable = false, updatable = false)
    private SubjectEntity subject;

    @Column(name = "subject_id", nullable = false)
    private UUID subjectId;

    @Column(name = "question", nullable = false)
    private String question;

    @Type(type = "jsonb")
    @Column(name = "answers", nullable = false, columnDefinition = "jsonb")
    private Answer answers;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TestType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "question_answer", nullable = false)
    private AnswerQuestion questionAnswer;
}
