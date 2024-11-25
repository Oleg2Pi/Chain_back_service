package by.polikarpov.backend.mapper;

import by.polikarpov.backend.dto.PersonsHomePageDto;
import by.polikarpov.backend.entity.Person;
import by.polikarpov.backend.entity.Work;
import by.polikarpov.backend.repository.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

    private final WorkRepository repository;

    @Autowired
    public PersonMapper(WorkRepository repository) {
        this.repository = repository;
    }

    public PersonsHomePageDto toDto(Person person) {
        if (person == null) {
            return null;
        }

        String imageFilePath = (person.getImage() != null) ?
                person.getImage().getFilePath() : null;
        String workFile = null;
        String workType = null;
        int workId = -1;

        if (person.getExecutor() != null && !person.getExecutor().getWorks().isEmpty()) {
            Work work = repository.findLastByExecutorId(person.getExecutor().getId());
            workId = work.getId();
            workType = work.getType();
            workFile = work.getFile();
        }

        return new PersonsHomePageDto(
                person.getChatId(),
                person.getFirstName(),
                person.getLastName(),
                imageFilePath,
                workId,
                workFile,
                workType
        );

    }
}
