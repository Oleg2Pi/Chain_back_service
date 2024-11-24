package by.polikarpov.backend.repository;

import by.polikarpov.backend.entity.Work;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkRepository extends JpaRepository<Work, Integer> {
}
