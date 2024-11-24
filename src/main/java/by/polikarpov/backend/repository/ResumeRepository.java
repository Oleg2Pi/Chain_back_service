package by.polikarpov.backend.repository;

import by.polikarpov.backend.entiity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume, Integer> {
}
