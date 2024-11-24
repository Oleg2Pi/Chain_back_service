package by.polikarpov.backend.service;

import by.polikarpov.backend.entity.Person;
import by.polikarpov.backend.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService implements CommonService<Person, Long> {

    private final PersonRepository repository;

    @Autowired
    public PersonService (PersonRepository repository) {
        this.repository = repository;
    }

    // person created in telegram-bot
    @Override
    public Person save(Person entity) {
        return repository.save(entity);
    }

    @Override
    public Person update(Long chatId, Person entity) {
        findPersonByChatId(chatId);
        entity.setChatId(chatId);
        return repository.save(entity);
    }

    @Override
    public Person findById(Long chatId) {
        return findPersonByChatId(chatId);
    }

    @Override
    public List<Person> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean deleteById(Long chatId) {
        findPersonByChatId(chatId);
        repository.deleteByChatId(chatId);
        return true;
    }

    private Person findPersonByChatId(Long chatId) {
        return repository.findByChatId(chatId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Person with chat_id: " + chatId + " not found"
                ));
    }
}
