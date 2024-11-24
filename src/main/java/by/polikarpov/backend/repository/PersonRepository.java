package by.polikarpov.backend.repository;

import by.polikarpov.backend.entiity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}
