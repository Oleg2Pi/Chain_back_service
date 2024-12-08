package by.polikarpov.backend.controller;

import by.polikarpov.backend.dto.PersonProfileDto;
import by.polikarpov.backend.dto.PersonsHomePageDto;
import by.polikarpov.backend.entity.Person;
import by.polikarpov.backend.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    private final PersonService service;

    @Autowired
    public PersonController (PersonService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAllPerson() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/home")
    public ResponseEntity<List<PersonsHomePageDto>> getHomePerson() {
        return ResponseEntity.ok(service.findAllByHomePage());
    }
    
    @GetMapping("/{chatId}")
    public ResponseEntity<PersonProfileDto> findProfile(@PathVariable Long chatId) {
        return ResponseEntity.ok(service.findProfileByChatId(chatId));
    }
}
