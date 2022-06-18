package hello.world.demo.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmailController {

    @Autowired
    private EmailServiceImpl emailService;

    @GetMapping(value = "/sendmail")
    public String sendmail() {

        emailService.sendMail("kate@example.com", "Test Subject", "Test mail");

        return "emailsent";
    }
}
