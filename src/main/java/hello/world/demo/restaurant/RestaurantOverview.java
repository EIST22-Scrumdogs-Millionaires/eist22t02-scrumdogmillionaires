package hello.world.demo.restaurant;

import hello.world.demo.querys.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RestaurantOverview {
    private static String filterType;
    private static List<Restaurant> restaurants;

    private static final Logger log = LoggerFactory.getLogger(RestaurantOverview.class);

    public static void selectRestaurant(Restaurant r) {

    }

    @Bean
    public static List<Restaurant> getAllRestaurants(RestaurantRepo repository) {
        List<Restaurant> ret = repository.findAll();
        if (ret.size() == 0) {
            Location l = new Location(10, 20, "München", "Horst Straße", "12", "7126");
            List<String> ratings = new ArrayList<>();
            ratings.add("Exzellent wie sonst was!");
            ratings.add("Sehr sehr gut!!");

            List<String> com = new ArrayList<>();
            com.add("Exzellent!");
            com.add("Sehr gut!!");

            List<String> pic = new ArrayList<>();

            repository.save(new Restaurant("DA VINCI", "Italienisches Restaurant und Pizzeria", l,
                    pic, ratings, com, LocalTime.of(13, 0), LocalTime.of(0, 0), "https://davincirestaurants.de", "$$$$"));

            ret = repository.findAll();
        }
        return ret;
    }

    private static List<Restaurant> convert (Iterable<Restaurant> i){
    
        List<Restaurant> ret = new ArrayList<>();
        for (Restaurant restaurant : i) {
            ret.add(restaurant);
        }
        return ret;
    }

    public static String getFilterType() {
        return filterType;
    }
}
