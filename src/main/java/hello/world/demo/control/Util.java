package hello.world.demo.control;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import hello.world.demo.model.Reservation;
import hello.world.demo.model.Restaurant;
import hello.world.demo.model.Visitor;

public class Util {

        public static List<LocalTime> getLocalTimeList(LocalTime mon, LocalTime tue, LocalTime wen, LocalTime thu,
                        LocalTime fri, LocalTime sat, LocalTime sun) {
                List<LocalTime> times = new ArrayList<>();
                times.add(mon);
                times.add(tue);
                times.add(wen);
                times.add(thu);
                times.add(fri);
                times.add(sat);
                times.add(sun);
                return times;
        }

        public static String reservationMail(Visitor user, Reservation reservation){
                String mailText = "Bitte bestätigen Sie Ihre Reservierung, " + user.getUsername() + "\n Tisch "
                        + reservation.getTable().getId() + " für " + reservation.getTable().getSeats() + " Person(en) \n"
                        + reservation.getDate() + " um " + reservation.getTime() + ". \n" + "Reservierungsname:"
                        + reservation.getUser().getUsername() + "\n Bestätigungsnummer: " + reservation.getId() + "\n"
                        + "Bitte bestätigen Sie Ihre Reservierung, indem Sie auf den folgenden Link klicken: LINK EINFÜGEN"
                        + "\n Wir freuen uns auf Sie!" + "\n\n"
                        + "Doch schon etwas Anderes vor? Sie können Ihre Reservierung bis zu 12h vorher stornieren, indem Sie auf den folgenden Link klicken. Es ist kinderleicht. Jetzt Reservierung stornieren:  localhost:8080/reservations/"
                        + reservation.getId() + "/" + reservation.getCancelSecretKey();
                return mailText;
        }

        public static String confirmMail(Visitor user, Reservation reservation, Restaurant restaurant){
                String mailText = " Ihre Reservierung ist bestätigt, " + user.getUsername()
                        + "!\n Vielen Dank dass Sie bei " + restaurant.getName() + "reserviert haben. \n Tisch "
                        + reservation.getTable().getId() + " für" + reservation.getTable().getSeats() + " Person(en) \n"
                        + reservation.getDate() + " um " + reservation.getTime() + ". \n" + "Reservierungsname: "
                        + reservation.getUser().getUsername() + "\n Bestätigungsnummer: " + reservation.getId() + "\n\n"
                        + "Wir freuen uns auf Sie!" + "\n\n"
                        + "Doch schon etwas Anderes vor? Sie können Ihre Reservierung bis zu 12h vorher stornieren, indem Sie auf den folgenden Link klicken. Es ist kinderleicht. Jetzt Reservierung stornieren:  localhost:8080/reservations/"
                        + reservation.getId() + "/" + reservation.getCancelSecretKey()
                        + " \n\n Was Sie vor Ihrem Besuch wissen sollten\n" +
                        "Der Tisch wird bis zu 15 Minuten nach Ihrer Reservierungszeit für Sie freigehalten. Bitte rufen Sie uns an, wenn Sie sich um mehr als 15 Minuten verspäten.\n"
                        +
                        "Der Tisch wird 2 Stunden für Sie reserviert.";
                return mailText;
        }

}
