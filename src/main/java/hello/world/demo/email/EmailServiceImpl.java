package hello.world.demo.email;

import hello.world.demo.restaurant.Reservation;
import hello.world.demo.restaurant.Visitor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailServiceImpl {

    private static JavaMailSender mailSender = getJavaMailSender();

    public static void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@scrumdogmillionaires.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    public static JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("my.gmail@gmail.com");
        mailSender.setPassword("password");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    public static void confirmReservation(Reservation reservation) {
    }

    public static void confirmCancellation(Reservation reservation) {
    }

    public static void sendEmail(Visitor user, String message) {
        sendSimpleMessage(user.getEmail(), "moin", "meister");
    }

    public static void verifyEmail(Visitor user, String message) {
    }
}
