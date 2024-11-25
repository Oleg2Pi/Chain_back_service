package by.polikarpov.backend.controller;

import by.polikarpov.backend.dto.WorkPageDto;
import by.polikarpov.backend.entity.Work;
import by.polikarpov.backend.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/portfolio")
public class WorkController {

    private final WorkService service;

    @Autowired
    public WorkController(WorkService service) {
        this.service = service;
    }

    @GetMapping("/work/{id}")
    public ResponseEntity<WorkPageDto> findById(@PathVariable int id) {
        return ResponseEntity.ok(service.findWorkAndWorksForPage(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Work>> findAllByExecutor(@PathVariable int id) {
        return ResponseEntity.ok(service.findAllByExecutorId(id));
    }

}
