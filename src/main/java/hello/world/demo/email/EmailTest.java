package hello.world.demo.email;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailTest {

    @Autowired
    private EmailServiceImpl emailService;

    @Test
    public void testEmail() {
        emailService.sendMail("frank23@example.com", "Test subject", "Test mail");
    }
}
