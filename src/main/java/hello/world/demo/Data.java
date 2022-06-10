package hello.world.demo;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import hello.world.demo.restaurant.Location;
import hello.world.demo.restaurant.Restaurant;
import hello.world.demo.restaurant.SmallRestaurant;
import hello.world.demo.restaurant.Tisch;

public class Data {

        private static List<Restaurant> sampleRestaurants = new ArrayList<>();

        public Data() {
                Location l = new Location(10, 20, "München", "Horst Straße", "12", "7126");
                List<String> ratings = new ArrayList<>();
                ratings.add("Exzellent wie sonst was!");
                ratings.add("Sehr sehr gut!!");

                List<String> com = new ArrayList<>();
                com.add("Exzellent!");
                com.add("Sehr gut!!");

                List<String> pic = new ArrayList<>();

                this.sampleRestaurants.add(
                                new Restaurant("DA VINCI", "Italienisches Restaurant und Pizzeria", l,
                                                pic, ratings, com, LocalTime.of(13, 0), LocalTime.of(0, 0),
                                                "https://davincirestaurants.de", "$$$$")

                );
                this.sampleRestaurants.add(
                                new Restaurant("MUN MUN", "Thailändisches Restaurant", null, null, null, null, null,
                                                null, "munmun.de", "$$"));
                this.sampleRestaurants
                                .add(new Restaurant("TÜRKITCH", "Köfte & Kebap", null, null, null, null, null, null,
                                                "tuerkitch-koeftekebap.de", "$"));
                this.sampleRestaurants.add(
                                new Restaurant("Augustiner Keller", "Historisches Restaurant mit großem Biergarten",
                                                null, null, null, null, null, null, "augustinerkeller.de", "$$$"));
        }

        public static synchronized List<SmallRestaurant> getAllRestaurants() {
                return sampleRestaurants.stream().map(x -> new SmallRestaurant(x.getName(), x.getDescription(),
                                x.getLocation(), x.getWebsite(), x.getPriceCategory())).toList();
        }

        public static synchronized Restaurant getRestaurant(String name) {
                List<Restaurant> ret = sampleRestaurants.stream().filter(x -> x.getName().equals(name)).toList();
                if (ret.size() == 0) {
                        return null;
                }
                return ret.get(0);
        }

        public static synchronized Restaurant getRestaurant(int id) {
                List<Restaurant> ret = sampleRestaurants.stream().filter(x -> x.getId() == id).toList();
                if (ret.size() == 0) {
                        return null;
                }
                return ret.get(0);
        }
}
