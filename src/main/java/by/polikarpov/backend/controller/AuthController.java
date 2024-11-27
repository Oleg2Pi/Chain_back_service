package by.polikarpov.backend.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping("/profile")
    public String getChatId(HttpSession session) {
        String chatId = (String) session.getAttribute("chatId");
        return chatId != null ? chatId : "Войдите в систему";
    }
}
