package hello.world.demo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.web.bind.annotation.RequestBody;
import hello.world.demo.restaurant.RestaurantOverview;
import hello.world.demo.restaurant.RestaurantType;
import hello.world.demo.restaurant.Review;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestaurantController {

    @GetMapping("restaurants/getTopTen")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> returngetTopTen() {

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        String jsonString = "didn't find anything";
        try {
            // RestaurantOverview.getAllRestaurants(new RestaurantRepoImpl())
            jsonString = mapper.writeValueAsString(RestaurantOverview.getTopTen());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonString);
    }

    @GetMapping("restaurants/search/{search}")
    @CrossOrigin(origins = "http://localhost:3000")
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
    @CrossOrigin(origins = "http://localhost:3000")
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
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> filterRestaurants(@PathVariable("search") String search,
            @PathVariable("filter") List<String> filter) {
        System.out.println(filter);

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        String jsonString = "didn't find anything";
        try {
            jsonString = mapper.writeValueAsString(RestaurantOverview.filter(search, filter));
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
    @CrossOrigin(origins = "http://localhost:3000")
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



    // Open question about how the review gets transmitted
    @PostMapping("comment/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String>  commentRestaurant( @RequestBody Review rev, @PathVariable("id") Integer id) {
        RestaurantOverview.addReview(id, rev);
        return ResponseEntity.ok("Ok");
    }

}
