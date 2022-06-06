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
        this.sampleRestaurants.add(new Restaurant("MUN MUN", "Thailändisches Restaurant", null, null, null, null, null,
                null, "munmun.de", "$$"));
        this.sampleRestaurants.add(new Restaurant("TÜRKITCH", "Köfte & Kebap", null, null, null, null, null, null,
                "tuerkitch-koeftekebap.de", "$"));
        this.sampleRestaurants.add(new Restaurant("Augustiner Keller", "Historisches Restaurant mit großem Biergarten",
                null, null, null, null, null, null, "augustinerkeller.de", "$$$"));
    }

    public static synchronized List<Restaurant> getAllRestaurants() {
        return sampleRestaurants;
    }

    public static synchronized Optional<Restaurant> getRestaurant(String name) {
        return sampleRestaurants.stream().filter(x -> x.getName().equals(name)).findFirst();
    }
}
