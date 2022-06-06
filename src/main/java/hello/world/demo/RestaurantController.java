package hello.world.demo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hello.world.demo.restaurant.Restaurant;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RestaurantController {
private Data data = new Data();

    @GetMapping("restaurants/getTopTen")
    public ResponseEntity<String> returngetTopTen() {
        data = new Data();
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        String jsonString = "didn't find anything";
        try {
            jsonString = mapper.writeValueAsString(Data.getAllRestaurants());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonString);
    }

    @GetMapping("restaurants/{pageSize}/{index}")
    public ResponseEntity<String> returnAllRestaurants( @PathVariable("pageSize") int pageSize, @PathVariable("index") int index) {
        data = new Data();
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        String jsonString = "didn't find anything";
        try {
            jsonString = mapper.writeValueAsString(Data.getAllRestaurants());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonString);
    }

    @GetMapping("restaurants/{id}")
    public ResponseEntity<String> returnRestaurant( @PathVariable("id") int id) {
        data = new Data();
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        String jsonString = "didn't find anything";
        try {
            jsonString = mapper.writeValueAsString(Data.getAllRestaurants());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonString);
    }
}