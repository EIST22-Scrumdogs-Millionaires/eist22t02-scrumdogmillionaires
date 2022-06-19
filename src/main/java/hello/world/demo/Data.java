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

                //MunMun Data
                Location locMunMun = new Location(48.162570, 11.586730, "München", "Münchner Freiheit", "7", "80802");
                List<Review> reviewsMunMun = new ArrayList<>();
                reviewsMunMun.add(new Review("Rico", "Exzellente Glasnudeln!", 5));
                reviewsMunMun.add(new Review("Patrick", "Sehr authentisches Curry!", 4));

                List<Tisch> tablesMunMUn = new ArrayList<>();
                tablesMunMUn.add(new Tisch(0, 5));

                LocalTime openingMunMun = LocalTime.of(11, 0);

                List<LocalTime> openingTimesMunMun = Util.getLocalTimeList(openingMunMun, openingMunMun, openingMunMun, openingMunMun, openingMunMun,
                        openingMunMun, openingMunMun);
                LocalTime closingMunMun = LocalTime.of(22, 0);

                List<LocalTime> closingTimesMunMun = Util.getLocalTimeList(closingMunMun, closingMunMun, closingMunMun, closingMunMun, closingMunMun,
                        closingMunMun, closingMunMun);

                List<Reservation> reservationsMunMun = new ArrayList<>();

                // Ende Data MunMun


                //Augustiner Data
                Location locAugustiner = new Location(48.143500, 11.551940, "München", "Arnulfstraße", "52", "80335");
                List<Review> reviewsAug = new ArrayList<>();
                reviewsAug.add(new Review("Rico", "Super Bier!", 3));
                reviewsAug.add(new Review("Simone", "Bayrisches Ambiente und fantastisches Bier", 4));

                List<Tisch> tablesAug = new ArrayList<>();
                tablesAug.add(new Tisch(0, 5));

                LocalTime openingAug = LocalTime.of(10, 0);

                List<LocalTime> openingTimesAug = Util.getLocalTimeList(openingAug, openingAug, openingAug, openingAug, openingAug,
                        openingAug, openingAug);
                LocalTime closingAug = LocalTime.of(1, 0);

                List<LocalTime> closingTimesAug = Util.getLocalTimeList(closingAug, closingAug, closingAug, closingAug, closingAug,
                        closingAug, closingAug);

                List<Reservation> reservationsAug = new ArrayList<>();
                // Ende Data Augustiner

                // Data Türkitchen
                Location locTurkitch = new Location(48.162570, 10.567500, "München", "Türkenstraße", "21", "80799");
                List<Review> reviewsTurkitch = new ArrayList<>();
                reviewsTurkitch.add(new Review("Ali", "Gute Falafel", 5));
                reviewsTurkitch.add(new Review("Tina", "Sehr gute Dönerbox!", 4));

                List<Tisch> tablesTurkitch = new ArrayList<>();
                tablesTurkitch.add(new Tisch(0, 5));

                LocalTime openingTurkitch = LocalTime.of(11, 0);

                List<LocalTime> openingTimesTurkitch = Util.getLocalTimeList(openingTurkitch, openingTurkitch, openingTurkitch, openingTurkitch, openingTurkitch,
                        openingTurkitch, openingTurkitch);

                LocalTime closingTurkitch = LocalTime.of(22, 0);

                List<LocalTime> closingTimesTurkitch = Util.getLocalTimeList(closingTurkitch, closingTurkitch, closingTurkitch, closingTurkitch, closingTurkitch,
                        closingTurkitch, closingTurkitch);

                List<Reservation> reservationsTurkitch = new ArrayList<>();



                sampleRestaurants.add(
                                new Restaurant(0, "DA VINCI", "Italienisches Restaurant und Pizzeria", l,
                                                Arrays.asList("https://isteam.wsimg.com/ip/8fe1339d-8c91-4097-84c7-25ef61943d99/1F273442-99D4-4460-9E86-B28E9DDB0641.jpeg/:/rs=w:360,h:270,cg:true,m/cr=w:360,h:270"),
                                                reviews, openingTimes, closingTimes,
                                                "https://davincirestaurants.de", "$$$$", tables, RestaurantType.ITALIAN,
                                                reservations)

                );
                sampleRestaurants.add(
                        new Restaurant(1, "MUN MUN", "Thailändisches Restaurant", locMunMun, Arrays.asList(
                                "https://media-cdn.tripadvisor.com/media/photo-s/0d/e1/71/1f/essen.jpg"),
                                reviewsMunMun, openingTimesMunMun,
                                closingTimesMunMun, "munmun.de", "$$", tablesMunMUn, RestaurantType.TAIWANESE,
                                reservationsMunMun));
                sampleRestaurants
                        .add(new Restaurant(2, "TÜRKITCH", "Köfte & Kebap", locTurkitch, Arrays.asList(
                                "https://www.bellacarne.it/wp-content/uploads/2021/03/kebab-ricetta-originale.jpg"),
                                reviewsTurkitch, openingTimesTurkitch, closingTimesTurkitch,
                                "tuerkitch-koeftekebap.de", "$", tablesTurkitch, RestaurantType.TAIWANESE,
                                reservationsTurkitch));
                sampleRestaurants.add(
                        new Restaurant(3, "Augustiner Keller", "Historisches Restaurant mit großem Biergarten",
                                locAugustiner,
                                Arrays.asList("https://www.merkur.de/bilder/2014/05/26/1236459/26415201-biergarten-augustiner-keller_20140519-112322-2pnSy8m1tIec.jpg"),
                                reviewsAug, openingTimesAug, closingTimesAug, "augustinerkeller.de", "$$$",
                                tablesAug, RestaurantType.BAVARIAN, reservationsAug));

                return sampleRestaurants;
        }

}
