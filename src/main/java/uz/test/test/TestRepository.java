package uz.test.test;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TestRepository extends JpaRepository<TestEntity, UUID> {
}
