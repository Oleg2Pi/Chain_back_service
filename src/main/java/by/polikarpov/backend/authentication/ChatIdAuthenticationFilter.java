package by.polikarpov.backend.authentication;

import by.polikarpov.backend.service.PersonService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

public class ChatIdAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final PersonService service;

    public ChatIdAuthenticationFilter(String defaultDefaultFilterProcessesUrl,
                                      AuthenticationManager authManager, PersonService service) {
        super(defaultDefaultFilterProcessesUrl);
        setAuthenticationManager(authManager);
        this.service = service;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        String chatId = request.getParameter("chatId");

        try {
            service.findProfileByChatId(Long.valueOf(chatId));
            var user = User.builder()
                    .username(chatId)
                    .password("")
                    .roles("USER")
                    .build();

            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                    user, null, user.getAuthorities()
            );

            HttpSession session = request.getSession();
            session.setAttribute("chatId", chatId);

            return getAuthenticationManager().authenticate(authRequest);
        } catch (NumberFormatException e) {
            throw new UsernameNotFoundException("User with id: " + chatId + " not found");
        }
    }
}
