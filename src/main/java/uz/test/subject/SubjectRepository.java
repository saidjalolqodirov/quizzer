package uz.test.subject;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SubjectRepository extends JpaRepository<SubjectEntity, UUID> {
    SubjectEntity findByIdAndDeletedFalse(UUID id);

    List<SubjectEntity> findByDeletedFalseOrderByCreatedDate();
}
