package hello.world.demo;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import hello.world.demo.restaurant.Location;
import hello.world.demo.restaurant.Reservation;
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

        List<Reservation> reservations = new ArrayList<>();

        sampleRestaurants.add(
                new Restaurant(0, "DA VINCI", "Italienisches Restaurant und Pizzeria", l,
                        pic, ratings, com, openingTimes, closingTimes,
                        "https://davincirestaurants.de", "$$$$", tables, RestaurantType.ITALIAN,
                        reservations)

        );
        sampleRestaurants.add(
                new Restaurant(1, "MUN MUN", "Thailändisches Restaurant", null, Arrays.asList("https://www.bellacarne.it/wp-content/uploads/2021/03/kebab-ricetta-originale.jpg"), Arrays.asList(1, 2, 3, 4, 4, 1, 2), null, null,
                        null, "munmun.de", "$$", tables, RestaurantType.TAIWANESE,
                        reservations));
        sampleRestaurants
                .add(new Restaurant(2, "TÜRKITCH", "Köfte & Kebap", null, null, null, null, null, null,
                        "tuerkitch-koeftekebap.de", "$", null, RestaurantType.TAIWANESE,
                        reservations));
        sampleRestaurants.add(
                new Restaurant(3, "Augustiner Keller", "Historisches Restaurant mit großem Biergarten",
                        null, null, null, null, null, null, "augustinerkeller.de", "$$$",
                        null, RestaurantType.BAVARIAN, reservations));

        return sampleRestaurants;
    }

}
