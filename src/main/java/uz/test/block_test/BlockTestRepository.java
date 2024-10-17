package uz.test.block_test;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BlockTestRepository extends JpaRepository<BlockTestEntity, UUID> {
}
