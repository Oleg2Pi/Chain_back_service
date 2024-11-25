package by.polikarpov.backend.repository;

import by.polikarpov.backend.entity.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WorkRepository extends JpaRepository<Work, Integer> {
    @Query("SELECT w FROM Work w WHERE w.executor.id = :executorId ORDER BY " +
           "w.dateAdded DESC LIMIT 1")
    Work findLastByExecutorId(Integer executorId);

    @Query("SELECT w FROM Work w WHERE w.id <> :workId ORDER BY " +
           "w.dateAdded DESC")
    List<Work> findAllWorksWithoutOne(Integer workId);
}
