package hello.world.demo;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import hello.world.demo.restaurant.Location;
import hello.world.demo.restaurant.Reservation;
import hello.world.demo.restaurant.Restaurant;
import hello.world.demo.restaurant.RestaurantType;
import hello.world.demo.restaurant.Review;
import hello.world.demo.restaurant.Tisch;

public class Data {

        public static List<Restaurant> generateRestaurants() {
                List<Restaurant> sampleRestaurants = new ArrayList<>();

                Location l = new Location(10, 20, "München", "Horst Straße", "12", "7126");

                List<Review> reviews = new ArrayList<>();
                reviews.add(new Review("Horst", "Exzellent", 5));
                reviews.add(new Review("Reinhard", "Sehr gut!", 4));

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
                                                Arrays.asList("https://isteam.wsimg.com/ip/8fe1339d-8c91-4097-84c7-25ef61943d99/1F273442-99D4-4460-9E86-B28E9DDB0641.jpeg/:/rs=w:360,h:270,cg:true,m/cr=w:360,h:270"),
                                                reviews, openingTimes, closingTimes,
                                                "https://davincirestaurants.de", "$$$$", tables, RestaurantType.ITALIAN,
                                                reservations)

                );
                sampleRestaurants.add(
                                new Restaurant(1, "MUN MUN", "Thailändisches Restaurant", null, Arrays.asList(
                                                "https://media-cdn.tripadvisor.com/media/photo-s/0d/e1/71/1f/essen.jpg"),
                                                null, null,
                                                null, "munmun.de", "$$", tables, RestaurantType.TAIWANESE,
                                                reservations));
                sampleRestaurants
                                .add(new Restaurant(2, "TÜRKITCH", "Köfte & Kebap", null, Arrays.asList(
                                                "https://www.bellacarne.it/wp-content/uploads/2021/03/kebab-ricetta-originale.jpg"),
                                                null, null, null,
                                                "tuerkitch-koeftekebap.de", "$", null, RestaurantType.TAIWANESE,
                                                reservations));
                sampleRestaurants.add(
                                new Restaurant(3, "Augustiner Keller", "Historisches Restaurant mit großem Biergarten",
                                                null,
                                                Arrays.asList("https://www.merkur.de/bilder/2014/05/26/1236459/26415201-biergarten-augustiner-keller_20140519-112322-2pnSy8m1tIec.jpg"),
                                                null, null, null, "augustinerkeller.de", "$$$",
                                                null, RestaurantType.BAVARIAN, reservations));

                return sampleRestaurants;
        }

}
