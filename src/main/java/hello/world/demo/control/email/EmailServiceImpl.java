package hello.world.demo.control.email;

import hello.world.demo.restaurant.Reservation;
import hello.world.demo.restaurant.Restaurant;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import hello.world.demo.model.Reservation;
import hello.world.demo.model.Restaurant;

import javax.mail.SendFailedException;
import java.time.format.DateTimeFormatter;
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
        try {
            mailSender.send(mailMessage);
        }catch (MailException e){
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
    public static String generateCalendarLink(Reservation reservation, Restaurant restaurant){
        String dateString = reservation.getDate().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String timeString = reservation.getTime().format(DateTimeFormatter.ofPattern("hhmm"));
        String endtimeString = reservation.getTime().plusHours(2).format(DateTimeFormatter.ofPattern("hhmm"));
        String endDateString;
        if(endtimeString.compareTo(timeString)<0){
            endDateString=reservation.getDate().plusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        }else {
            endDateString=dateString;
        }
        System.out.println(dateString);
       return changeWhitespacesToPlus("https://calendar.google.com/calendar/r/eventedit?text="+restaurant.getName()+"+Reservierung&dates="+dateString+"T"+timeString+"00Z/"+endDateString +"T" +endtimeString +"00Z&details=We confirm the reservation of a table for "+reservation.getTable().getSeats()+" people in the "+restaurant.getName()+
               "&location="+restaurant.getLocation().getCity()+"+"+restaurant.getLocation().getPlz()+"+"+restaurant.getLocation().getStreet()+"+"+restaurant.getLocation().getStreetnumber());
    }
    public static String changeWhitespacesToPlus(String s){
        return s.replace(' ','+');
    }

}
