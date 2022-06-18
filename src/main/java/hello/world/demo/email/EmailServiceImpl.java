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

    public static void sendMail(String toEmail, String subject, String message) {
        var mailMessage = new SimpleMailMessage();
        mailMessage.setTo(toEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailMessage.setFrom("scrumdogmillionaries@yahoo.com");
        mailSender.send(mailMessage);
    }

    public static JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.mail.yahoo.com");
        mailSender.setPort(465);

        mailSender.setUsername("scrumdogmillionaries@yahoo.com");
        mailSender.setPassword("gzxvbavnrturnlzm");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.startssl.enable", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    public static void confirmReservation(Reservation reservation) {
    }

    public static void confirmCancellation(Reservation reservation) {
    }

    public static void sendEmail(Visitor user, String message) {
        sendMail(user.getEmail(), "moin", "meister");
    }

    public static void verifyEmail(Visitor user, String message) {
    }
}
