package by.polikarpov.backend.service;

import by.polikarpov.backend.dto.WorkPageDto;
import by.polikarpov.backend.entity.Work;
import by.polikarpov.backend.mapper.ChosenWorkMapper;
import by.polikarpov.backend.repository.WorkRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkService implements CommonService<Work, Integer>{

    private final WorkRepository repository;
    private final ChosenWorkMapper mapper;

    @Autowired
    public WorkService(WorkRepository repository, ChosenWorkMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Work save(Work entity) {
        return null;
    }

    @Override
    public Work update(Integer id, Work entity) {
        return null;
    }

    @Override
    public Work findById(Integer id) {
        return null;
    }

    @Override
    public List<Work> findAll() {
        return List.of();
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }

    public WorkPageDto findWorkAndWorksForPage(int id) {
        Work work = findWorkById(id);
        return mapper.toDto(work);
    }

    private Work findWorkById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                "Work with id: " + id + " not found"
        ));
    }
}
