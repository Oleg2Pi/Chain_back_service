package by.polikarpov.backend.repository;

import by.polikarpov.backend.entity.ImageOfPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ImageOfPersonRepository extends JpaRepository<ImageOfPerson, Integer> {
    @Query("SELECT i FROM ImageOfPerson i WHERE i.person.chatId = :chatId")
    ImageOfPerson findByPersonChatId(Long chatId);
}
