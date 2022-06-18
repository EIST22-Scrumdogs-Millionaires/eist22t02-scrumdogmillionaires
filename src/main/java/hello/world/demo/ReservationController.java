package hello.world.demo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import hello.world.demo.email.EmailServiceImpl;
import hello.world.demo.restaurant.Reservation;
import hello.world.demo.restaurant.RestaurantOverview;

import hello.world.demo.restaurant.Visitor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationController {

    @GetMapping("reservations/{id}")
    public ResponseEntity<String> returnReservation(@PathVariable("id") int id) {

        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        String jsonString = "didn't find anything";
        try {
            jsonString = mapper.writeValueAsString(RestaurantOverview.getReservation(id));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonString);
    }

    @DeleteMapping("reservations/{id}/{cancleSecretKey}")
    public void removeReservation(@PathVariable("id") int id,
            @PathVariable("cancleSecretKey") String cancleSecretKey) {
        RestaurantOverview.cancelReservation(id, cancleSecretKey);
    }

    //Open question about how the user gets transmitted
     @PostMapping("reservations")
     public ResponseEntity<String> postReservation(@RequestBody Reservation reservation,
                                                   @RequestBody Visitor visitor)
     {

     ObjectMapper mapper = new ObjectMapper();
     mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

     String jsonString = "didn't find anything";
     try {
     jsonString = mapper.writeValueAsString(RestaurantOverview.postReservation(reservation,visitor));
     } catch (JsonProcessingException e) {
     e.printStackTrace();
     }
     return ResponseEntity.ok(jsonString);
     }

    @GetMapping(value = "/sendmail")
    public String sendmail() {

        EmailServiceImpl.sendMail("caroline.stallknecht@gmail.com", "Test Subject", "Test mail");

        return "emailsent";
    }
}
