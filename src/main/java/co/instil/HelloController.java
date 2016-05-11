package co.instil;

import com.codahale.metrics.annotation.Timed;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @Timed
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
