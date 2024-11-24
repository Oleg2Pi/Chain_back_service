package by.polikarpov.backend.repository;

import by.polikarpov.backend.entiity.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStatusRepository extends JpaRepository<UserStatus, Integer> {
}
