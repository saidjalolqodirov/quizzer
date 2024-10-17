package uz.test.block_test;

import org.springframework.stereotype.Service;
import uz.test.exception.NotFountException;
import uz.test.subject.SubjectRepository;

import java.util.List;
import java.util.UUID;

@Service
public class BlockTestService {
    private final BlockTestRepository blockTestRepository;
    private final SubjectRepository subjectRepository;

    public BlockTestService(BlockTestRepository blockTestRepository, SubjectRepository subjectRepository) {
        this.blockTestRepository = blockTestRepository;
        this.subjectRepository = subjectRepository;
    }

    public BlockTestEntity create(BlockTestRequest request) {
        subjectRepository.findById(request.getSubjectId()).orElseThrow(() -> new NotFountException("block test not found"));
        BlockTestEntity blockTest = requestDtoToModel(request, new BlockTestEntity());
        return blockTestRepository.save(blockTest);
    }

    private BlockTestEntity requestDtoToModel(BlockTestRequest request, BlockTestEntity blockTestEntity) {
        blockTestEntity.setSubjectId(request.getSubjectId());
        blockTestEntity.setName(request.getName());
        return blockTestEntity;
    }

    public BlockTestEntity update(UUID id, BlockTestRequest request) {
        BlockTestEntity blockTest = blockTestRepository.findById(id).orElseThrow(() -> new NotFountException("block test not found"));
        requestDtoToModel(request, blockTest);
        return blockTestRepository.save(blockTest);
    }

    public void delete(UUID id) {
        BlockTestEntity blockTest = blockTestRepository.findById(id).orElseThrow(() -> new NotFountException("block test not found"));
        blockTestRepository.delete(blockTest);
    }

    public BlockTestEntity getOne(UUID id) {
        return blockTestRepository.findById(id).orElseThrow(() -> new NotFountException("block test not found"));
    }

    public List<BlockTestEntity> list() {
        return blockTestRepository.findAll();
    }
}
