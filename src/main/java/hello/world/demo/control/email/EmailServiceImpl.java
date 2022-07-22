package hello.world.demo.control.email;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import hello.world.demo.model.Reservation;
import hello.world.demo.model.Restaurant;

import java.time.format.DateTimeFormatter;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl {

    private static JavaMailSender mailSender = getJavaMailSender();
    /**
     * Sends the given HTML email
     * 
     * we took some inspiration from this source:
     * https://stackoverflow.com/questions/5289849/how-do-i-send-html-email-in-spring-mvc
     * 
     * @param toEmail
     * @param subject
     * @param message
     */
    public static void sendMail(String toEmail, String subject, String message) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        // mimeMessage.setContent(htmlMsg, "text/html"); /** Use this or below line **/

        try {
            helper.setText(message, true); // Use this or above line.
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setFrom("scrumdogmillionaries@yahoo.com");
            mailSender.send(mimeMessage);
        } catch (MailException e) {
            System.out.println("Mail not sent");
        } catch (MessagingException e) {
            System.out.println("Mail not sent");
        }
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

    /**
     * Creates a calendar link for the reservation, based on :
     * https://stackoverflow.com/questions/5831877/how-do-i-create-a-link-to-add-an-entry-to-a-calendar/19867654#19867654
     * 
     * @param reservation
     * @param restaurant
     * @return
     */

    public static String generateCalendarLink(Reservation reservation, Restaurant restaurant) {
        String dateString = reservation.getDate().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String timeString = reservation.getTime().format(DateTimeFormatter.ofPattern("hhmm"));
        String endtimeString = reservation.getTime().plusHours(2).format(DateTimeFormatter.ofPattern("hhmm"));
        String endDateString;
        if (endtimeString.compareTo(timeString) < 0) {
            endDateString = reservation.getDate().plusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        } else {
            endDateString = dateString;
        }
        System.out.println(dateString);
        return changeWhitespacesToPlus("https://calendar.google.com/calendar/r/eventedit?text=" + restaurant.getName()
                + "+Reservierung&dates=" + dateString + "T" + timeString + "00Z/" + endDateString + "T" + endtimeString
                + "00Z&details=We confirm the reservation of a table for " + reservation.getTable().getSeats()
                + " people in the " + restaurant.getName() +
                "&location=" + restaurant.getLocation().getCity() + "+" + restaurant.getLocation().getPlz() + "+"
                + restaurant.getLocation().getStreet() + "+" + restaurant.getLocation().getStreetnumber());
    }

    public static String changeWhitespacesToPlus(String s) {
        return s.replace(' ', '+');
    }

}
