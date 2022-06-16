package hello.world.demo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import hello.world.demo.querys.RestaurantRepo;

import hello.world.demo.restaurant.RestaurantOverview;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RestaurantController {
private Data data = new Data();
private RestaurantRepo restaurantRepo;

    @GetMapping("restaurants/getTopTen")
    public ResponseEntity<String> returngetTopTen() {
    
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        String jsonString = "didn't find anything";
        try {
            //RestaurantOverview.getAllRestaurants(new RestaurantRepoImpl())
            jsonString = mapper.writeValueAsString(RestaurantOverview.getAllRestaurants(restaurantRepo));
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
            jsonString = mapper.writeValueAsString(RestaurantOverview.getRestaurantById(id));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonString);
    }
/*
    @GetMapping("restaurants/{id}")
    public ResponseEntity<String> findRestaurant( @PathVariable("search") String search) {
        data = new Data();
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        String jsonString = "didn't find anything";
        try {
            jsonString = mapper.writeValueAsString(Data.getRestaurant(search));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(jsonString);
    }

 */
}
