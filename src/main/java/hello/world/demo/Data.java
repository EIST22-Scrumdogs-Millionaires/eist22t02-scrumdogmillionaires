package hello.world.demo;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import hello.world.demo.restaurant.Location;
import hello.world.demo.restaurant.Restaurant;
import hello.world.demo.restaurant.Tisch;

public class Data {

        public static List<Restaurant> generateRestaurants() {
                List<Restaurant> sampleRestaurants = new ArrayList<>();

                Location l = new Location(10, 20, "München", "Horst Straße", "12", "7126");
                List<String> ratings = new ArrayList<>();
                ratings.add("Exzellent wie sonst was!");
                ratings.add("Sehr sehr gut!!");

                List<String> com = new ArrayList<>();
                com.add("Exzellent!");
                com.add("Sehr gut!!");

                List<String> pic = new ArrayList<>();

                List<Tisch> tables = new ArrayList<>();
                tables.add(new Tisch(0, 5));

                sampleRestaurants.add(
                                new Restaurant("DA VINCI", "Italienisches Restaurant und Pizzeria", l,
                                                pic, ratings, com, LocalTime.of(13, 0), LocalTime.of(0, 0),
                                                "https://davincirestaurants.de", "$$$$", tables)

                );
                sampleRestaurants.add(
                                new Restaurant("MUN MUN", "Thailändisches Restaurant", null, null, null, null, null,
                                                null, "munmun.de", "$$", tables));
                sampleRestaurants
                                .add(new Restaurant("TÜRKITCH", "Köfte & Kebap", null, null, null, null, null, null,
                                                "tuerkitch-koeftekebap.de", "$", null));
                sampleRestaurants.add(
                                new Restaurant("Augustiner Keller", "Historisches Restaurant mit großem Biergarten",
                                                null, null, null, null, null, null, "augustinerkeller.de", "$$$",
                                                null));

                return sampleRestaurants;
        }

}
