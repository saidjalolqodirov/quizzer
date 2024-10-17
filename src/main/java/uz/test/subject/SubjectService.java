package uz.test.subject;

import org.springframework.stereotype.Service;
import uz.test.exception.NotFountException;

import java.util.List;
import java.util.UUID;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public SubjectEntity create(SubjectRequest request) {
        SubjectEntity subjectEntity = new SubjectEntity();
        subjectEntity.setName(request.getName());
        return subjectRepository.save(subjectEntity);
    }

    public SubjectEntity update(String id, SubjectRequest request) {
        SubjectEntity subject = findById(id);
        subject.setName(request.getName());
        return subjectRepository.save(subject);
    }

    public SubjectEntity findById(String id) {
        SubjectEntity subject = subjectRepository.findByIdAndDeletedFalse(UUID.fromString(id));
        if (subject == null) {
            throw new NotFountException("subject_not_found");
        }
        return subject;
    }

    public List<SubjectEntity> list() {
        return subjectRepository.findByDeletedFalseOrderByCreatedDate();
    }

    public void delete(String id) {
        SubjectEntity subject = findById(id);
        subjectRepository.delete(subject);
    }
}
