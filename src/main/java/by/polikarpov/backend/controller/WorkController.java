package by.polikarpov.backend.controller;

import by.polikarpov.backend.dto.WorkPageDto;
import by.polikarpov.backend.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/work")
public class WorkController {

    private final WorkService service;

    @Autowired
    public WorkController(WorkService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkPageDto> findById(@PathVariable int id) {
        return ResponseEntity.ok(service.findWorkAndWorksForPage(id));
    }

}
