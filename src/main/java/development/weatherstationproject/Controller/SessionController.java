package development.weatherstationproject.Controller;


import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SessionController {


    @GetMapping("/session")
    public String getSession(HttpSession session) {
        return "Session ID: " + session.getId();
    }

}
