package by.polikarpov.backend.service;

import by.polikarpov.backend.dto.PersonProfileDto;
import by.polikarpov.backend.dto.PersonsHomePageDto;
import by.polikarpov.backend.entity.Executor;
import by.polikarpov.backend.entity.Person;
import by.polikarpov.backend.mapper.PersonMapper;
import by.polikarpov.backend.repository.ExecutorRepository;
import by.polikarpov.backend.repository.PersonRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService implements CommonService<Person, Long> {

    private final PersonRepository personRepository;
    private final PersonMapper mapper;
    private final ExecutorRepository executorRepository;

    @Autowired
    public PersonService (PersonRepository personRepository, PersonMapper mapper,
                          ExecutorRepository executorRepository) {
        this.mapper = mapper;
        this.personRepository = personRepository;
        this.executorRepository = executorRepository;
    }

    // person created in telegram-bot
    @Override
    public Person save(Person entity) {
        return personRepository.save(entity);
    }

    @Override
    public Person update(Long chatId, Person entity) {
        findPersonByChatId(chatId);
        entity.setChatId(chatId);
        return personRepository.save(entity);
    }

    @Override
    public Person findById(Long chatId) {
        return findPersonByChatId(chatId);
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public boolean deleteById(Long chatId) {
        findPersonByChatId(chatId);
        personRepository.deleteByChatId(chatId);
        return true;
    }

    private Person findPersonByChatId(Long chatId) {
        return personRepository.findByChatId(chatId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Person with chat_id: " + chatId + " not found"
                ));
    }

    public List<PersonsHomePageDto> findAllByHomePage() {
        return personRepository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    public PersonProfileDto findProfileByChatId(Long chatId) {
        Person person = findPersonByChatId(chatId);
        return mapper.toDtoProfile(person);
    }


    public void createPerson(Long chatId, String firstName, String lastName, String usernameTG,
                             String phone, String work) {
        if (personRepository.findByChatId(chatId).isPresent()) {
                throw new EntityExistsException("Person with chat_id: " + chatId + " already exists");
            }

            Person person = Person.builder()
                    .chatId(chatId)
                    .firstName(firstName)
                    .lastName(lastName)
                    .usernameTG(usernameTG)
                    .phone(phone)
                    .build();

            personRepository.save(person);

            if (work.equals("executor")) {
                Executor executor = Executor.builder()
                        .person(person)
                        .build();
                executorRepository.save(executor);
            }


    }
}
