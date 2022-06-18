package hello.world.demo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import hello.world.demo.restaurant.RestaurantOverview;
import hello.world.demo.restaurant.RestaurantType;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestaurantController {

    @GetMapping("restaurants/getTopTen")
    public ResponseEntity<String> returngetTopTen() {

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        String jsonString = "didn't find anything";
        try {
            // RestaurantOverview.getAllRestaurants(new RestaurantRepoImpl())
            jsonString = mapper.writeValueAsString(RestaurantOverview.getAllRestaurants());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonString);
    }

    @GetMapping("restaurants/search/{search}")
    public ResponseEntity<String> returnRestaurant(@PathVariable("search") String search) {

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        String jsonString = "didn't find anything";
        try {
            jsonString = mapper.writeValueAsString(RestaurantOverview.search(search));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonString);
    }

    @GetMapping("restaurants/getRestaurantTypes")
    public ResponseEntity<String> getRestaurantTypes() {

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        String jsonString = "didn't find anything";
        try {
            jsonString = mapper.writeValueAsString(RestaurantType.values());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonString);
    }

    @GetMapping("restaurants/search/{search}/{filter}")
    public ResponseEntity<String> filterRestaurants(@PathVariable("search") String search,
            @PathVariable("filter") List<String> filter) {

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        String jsonString = "didn't find anything";
        try {
            jsonString = mapper.writeValueAsString(RestaurantOverview.search(search));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonString);
    }

    // Dont need that do we? We hope not ;)
    // @GetMapping("restaurants/{pageSize}/{index}")
    // public ResponseEntity<String> returnAllRestaurants(@PathVariable("pageSize")
    // int pageSize,
    // @PathVariable("index") int index) {

    // ObjectMapper mapper = new ObjectMapper();
    // mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

    // String jsonString = "didn't find anything";
    // try {
    // jsonString = mapper.writeValueAsString(Data.getAllRestaurants());
    // } catch (JsonProcessingException e) {
    // e.printStackTrace();
    // }
    // return ResponseEntity.ok(jsonString);
    // }

    @GetMapping("restaurant/{id}")
    public ResponseEntity<String> returnRestaurant(@PathVariable("id") int id) {

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        String jsonString = "didn't find anything";
        try {
            jsonString = mapper.writeValueAsString(RestaurantOverview.getRestaurantById(id));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonString);
    }
    /*
     * @GetMapping("restaurants/{id}")
     * public ResponseEntity<String> findRestaurant( @PathVariable("search") String
     * search) {
     * data = new Data();
     * ObjectMapper mapper = new ObjectMapper();
     * mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
     * 
     * String jsonString = "didn't find anything";
     * try {
     * jsonString = mapper.writeValueAsString(Data.getRestaurant(search));
     * } catch (JsonProcessingException e) {
     * e.printStackTrace();
     * }
     * return ResponseEntity.ok(jsonString);
     * }
     * 
     */
}
