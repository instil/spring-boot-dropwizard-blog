package co.instil;

import com.codahale.metrics.annotation.Timed;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {
    @Timed(name = "Timer")
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
