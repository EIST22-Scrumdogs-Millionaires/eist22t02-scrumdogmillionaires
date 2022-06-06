package hello.world.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import hello.world.demo.restaurant.Restaurant;

public class Data {

    private static List<Restaurant> sampleRestaurants = new ArrayList<>();

    public Data() {
        this.sampleRestaurants.add(
                new Restaurant("DA VINCI", "Italienisches Restaurant und Pizzeria", null,
                        null, null, null, null, null, "https://davincirestaurants.de", "$$")

        );
    }

    public static synchronized List<Restaurant> getAllRestaurants() {
        return sampleRestaurants;
    }

    public static synchronized Optional<Restaurant> getRestaurant(String name) {
        return sampleRestaurants.stream().filter(x -> x.getName().equals(name)).findFirst();
    }
}
