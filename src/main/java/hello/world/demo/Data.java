package hello.world.demo;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import hello.world.demo.restaurant.Location;
import hello.world.demo.restaurant.Restaurant;
import hello.world.demo.restaurant.RestaurantType;
import hello.world.demo.restaurant.Tisch;

public class Data {

        public static List<Restaurant> generateRestaurants() {
                List<Restaurant> sampleRestaurants = new ArrayList<>();

                Location l = new Location(10, 20, "München", "Horst Straße", "12", "7126");
                List<Integer> ratings = new ArrayList<>();
                ratings.add(5);
                ratings.add(4);

                List<String> com = new ArrayList<>();
                com.add("Exzellent!");
                com.add("Sehr gut!!");

                List<String> pic = new ArrayList<>();

                List<Tisch> tables = new ArrayList<>();
                tables.add(new Tisch(0, 5));

                LocalTime opening = LocalTime.of(13, 0);

                List<LocalTime> openingTimes = Util.getLocalTimeList(opening, opening, opening, opening, opening,
                                opening, opening);

                LocalTime closing = LocalTime.of(13, 0);

                List<LocalTime> closingTimes = Util.getLocalTimeList(closing, closing, closing, closing, closing,
                                closing, closing);

                sampleRestaurants.add(
                                new Restaurant("DA VINCI", "Italienisches Restaurant und Pizzeria", l,
                                                pic, ratings, com, openingTimes, closingTimes,
                                                "https://davincirestaurants.de", "$$$$", tables, RestaurantType.ITALIAN)

                );
                sampleRestaurants.add(
                                new Restaurant("MUN MUN", "Thailändisches Restaurant", null, null, null, null, null,
                                                null, "munmun.de", "$$", tables, RestaurantType.TAIWANESE));
                sampleRestaurants
                                .add(new Restaurant("TÜRKITCH", "Köfte & Kebap", null, null, null, null, null, null,
                                                "tuerkitch-koeftekebap.de", "$", null, RestaurantType.TAIWANESE));
                sampleRestaurants.add(
                                new Restaurant("Augustiner Keller", "Historisches Restaurant mit großem Biergarten",
                                                null, null, null, null, null, null, "augustinerkeller.de", "$$$",
                                                null, RestaurantType.BAVARIAN));

                return sampleRestaurants;
        }

}
