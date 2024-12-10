package by.polikarpov.backend.controller;

import by.polikarpov.backend.entity.Person;
import by.polikarpov.backend.service.PersonService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final PersonService service;

    @Autowired
    public AuthController(PersonService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody Map<String, String> payload, HttpSession session) {
        String chatId = payload.get("chatId");

        if (chatId.isEmpty()) {
            System.out.println("ChatId: " + chatId);
            return ResponseEntity.badRequest().build();
        }

        System.out.println(chatId);

        session.setAttribute("chatId", chatId);

        return ResponseEntity.ok().build();
    }

    @GetMapping("profile")
    public ResponseEntity<Long> getChatId(HttpSession session) {
        String chatId = (String) session.getAttribute("chatId");
        System.out.println("ChatId: " + chatId);

        if (chatId == null) {
            return ResponseEntity.notFound().build();
        }

        Person person = service.findById(Long.parseLong(chatId));

        if (person == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(Long.parseLong(chatId));
    }

}
