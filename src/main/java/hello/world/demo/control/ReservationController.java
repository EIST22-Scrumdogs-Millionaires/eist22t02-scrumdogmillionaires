package hello.world.demo.control;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import hello.world.demo.control.email.EmailServiceImpl;
import hello.world.demo.control.email.EmailThread;
import hello.world.demo.model.Email;
import hello.world.demo.model.Reservation;
import hello.world.demo.model.Restaurant;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationController {

    @GetMapping("reservations/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> returnReservation(@PathVariable("id") int id) {

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        String jsonString = "didn't find anything";
        try {
            jsonString = mapper.writeValueAsString(RestaurantOverview.getReservation(id));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonString);
    }

    @GetMapping("reservations/getAvailableTables/{restaurant_id}/{date}/{time}/{seats}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> AvailableTables(@PathVariable("restaurant_id") int restaurant_id,
            @PathVariable("date") String date, @PathVariable("time") String time,
            @PathVariable("seats") int seats) {

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        String jsonString = "didn't find anything";
        try {
            jsonString = mapper
                   .writeValueAsString(RestaurantOverview.getAvailableTables(restaurant_id,
                           LocalDate.of(Integer.parseInt(date.substring(6,10)),Integer.parseInt(date.substring(3,5)),Integer.parseInt(date.substring(0,2))),
                           LocalTime.of(Integer.parseInt(time.substring(0,2)),Integer.parseInt(time.substring(3,5))),
                           seats));

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(jsonString);
        return ResponseEntity.ok(jsonString);
    }

    @DeleteMapping("reservations/{id}/{actionSecretKey}")
    @CrossOrigin(origins = "http://localhost:3000")
    public void removeReservation(@PathVariable("id") int id,
            @PathVariable("actionSecretKey") String actionSecretKey) {
        RestaurantOverview.performActionOnReservation(id, actionSecretKey);
    }

    

    // Open question about how the user gets transmitted
    @PostMapping("reservations")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> postReservation(@RequestBody Reservation reservation
          ) {

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        String jsonString = "didn't find anything";
        try {
            jsonString = mapper.writeValueAsString(RestaurantOverview.postReservation(reservation));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Restaurant restaurant = Data.getRestaurants().get(reservation.getRestaurant_id());
        EmailServiceImpl.sendMail(reservation.getUser().getEmail(), "Tisch Reservierung bei "+restaurant.getName(),
                "Klicken Sie diesen Link um ein Kalender-Event hinzuzufügen:"+EmailServiceImpl.generateCalendarLink(reservation,restaurant));
        return ResponseEntity.ok(jsonString);
    }

    @GetMapping(value = "/sendmailTest")
    @CrossOrigin(origins = "http://localhost:3000")
    public String sendmail() {

        EmailThread t = new EmailThread();

        for (int i = 0; i < 100; i++) {
            EmailThread.addEmail(
                    new Email("simone.domenici@aol.com", "Spam", "Spam" + i, LocalDate.now(), LocalTime.now()));
        }

        t.start();

         EmailServiceImpl.sendMail("caroline.stallknecht@gmail.com", "Test Subject",
         "Test mail");

        return "emailsent";
    }
}
