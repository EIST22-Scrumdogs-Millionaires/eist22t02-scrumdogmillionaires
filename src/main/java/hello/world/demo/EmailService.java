package hello.world.demo;

import hello.world.demo.restaurant.Reservation;
import hello.world.demo.restaurant.Visitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;




@Component
public class EmailService {


    @Autowired
    //private JavaMailSender mailsender;




    public static void confirmReservation(Reservation reservation) {
    }

    public static void confirmCancellation(Reservation reservation) {
    }

    public static void sendEmail(Visitor user, String message) {
    }

    public static void verifyEmail(Visitor user, String message) {
    }
}
