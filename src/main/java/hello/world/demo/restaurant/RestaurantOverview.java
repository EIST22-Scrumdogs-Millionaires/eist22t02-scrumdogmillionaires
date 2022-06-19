package hello.world.demo.restaurant;

import hello.world.demo.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class RestaurantOverview {
    private static List<Restaurant> restaurants = Data.generateRestaurants();

    private final static int MAX_DIFFERENCE = 30;
    private final static int TOP_TEN = 10;

    public static Restaurant getRestaurantById(int id) {
        List<Restaurant> ret = restaurants.stream().filter(x -> x.getId() == id).toList();
        if (ret.size() == 0) {
            return null;
        }
        return ret.get(0);
    }

    // TODO: null checks
    public static List<SmallRestaurant> getAllRestaurants() {
        return restaurants.stream().map(x -> new SmallRestaurant(x.getId(), x.getName(), x.getDescription(),
                x.getLocation(), x.getWebsite(), x.getPriceCategory(), x.getAverageRating(), x.getRestaurantType(),
                x.getPictures()))
                .toList();
    }

    public static List<SmallRestaurant> getTopTen() {
        return restaurants.stream().map(x -> new SmallRestaurant(x.getId(), x.getName(), x.getDescription(),
                x.getLocation(), x.getWebsite(), x.getPriceCategory(), x.getAverageRating(), x.getRestaurantType(),
                x.getPictures()))
                .sorted((a, b) -> (int) ((a.getAverageRating() * 1000d) - (b.getAverageRating() * 1000d))).limit(10)
                .toList();

    }

    /*
     * Filterype Syntax:
     * T_t: RestaurantType
     * P_p: Prce Category
     * D_x_y_d: Distance, x and y coordinates and distnace in km respectively
     * A_r: Average Rating
     * F_f_d_p: Free time slots, timefrom, date and number of persons
     */
    public static List<SmallRestaurant> filter(String searchQuery, List<String> filterTypes) {
        List<Restaurant> ret;
        if (searchQuery.isBlank()) {
            ret = restaurants;
        } else {
            ret = searchB(searchQuery);
        }
        for (String filterType : filterTypes) {

            switch (filterType.charAt(0)) {
                case 'T': {
                    RestaurantType restaurantType = RestaurantType.valueOf(getArgument(filterType, 0));
                    ret = ret.stream().filter(x -> x.getRestaurantType() == restaurantType).toList();
                    break;
                }
                case 'P': {
                    String priceCategory = getArgument(filterType, 0);
                    ret = ret.stream().filter(x -> x.getPriceCategory().compareTo(priceCategory) == 0).toList();
                    break;
                }
                case 'D': {
                    Double x = Double.parseDouble(getArgument(filterType, 0));
                    Double y = Double.parseDouble(getArgument(filterType, 1));
                    Double dis = Double.parseDouble(getArgument(filterType, 2));
                    ret = ret.stream().filter(l -> distance(l.getLocation().getXcoordinate(), x,
                            l.getLocation().getYcoordinate(), y, dis)).toList();
                    break;
                }
                case 'A': {
                    Double avg = Double.parseDouble(getArgument(filterType, 0));
                    ret = ret.stream().filter(l -> l.getAverageRating() >= avg).toList();
                    break;
                }
                case 'F': {
                    LocalTime time = LocalTime.parse((getArgument(filterType, 0)));
                    LocalDate date = LocalDate.parse((getArgument(filterType, 1)));
                    Integer pers = Integer.parseInt(getArgument(filterType, 2));
                    ret = ret.stream().filter(l -> l.checkAvailability(time, date, pers)).toList();
                    break;
                }
            }
        }
        return ret.stream().map(x -> new SmallRestaurant(x.getId(), x.getName(), x.getDescription(),
                x.getLocation(), x.getWebsite(), x.getPriceCategory(), x.getAverageRating(), x.getRestaurantType(),
                x.getPictures()))
                .toList();

    }

    private static String getArgument(String src, int num) {
        String ret = "";
        while (num > 0) {
            src = src.substring(src.indexOf('_' + 1, 0));
            if (src.lastIndexOf('_') != -1 && num == 0) {
                src = src.substring(0, src.indexOf('_', 0));
            }
            num--;
        }
        return ret;
    }

    /**
     * Calculate distance between two points in latitude and longitude using
     * Haversine method as its base.
     *
     * @param lat1 Latidue one
     * @param lat2 Latidue two
     * @param lon1 Longitude one
     * @param lon2 Longitude one
     * @param dis  maximal distance
     * @returns true if distance in KM is smaller equal than the passed distance,
     *          otherwise false
     */
    public static boolean distance(double lat1, double lat2, double lon1,
            double lon2, double dis) {

        final int R = 6371; // Radius of the earth in km

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                        * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        return (R * 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))) <= dis;
    }

    public static List<Restaurant> searchB(String searchQuery) {
        int difference = 0;
        List<Restaurant> results = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            String restaurantName = restaurant.getName();
            difference = calculate(searchQuery, restaurantName);
            if (difference <= MAX_DIFFERENCE) {
                results.add(restaurant);
            }
        }
        Collections.reverse(results);

        // gib top ten 10
        if (results.size() > TOP_TEN) {
            ((ArrayList<Restaurant>) results).subList(0, TOP_TEN);
        }

        return results;
    }

    public static List<SmallRestaurant> search(String searchQuery) {
        int difference = 0;
        List<SmallRestaurant> results = new ArrayList<>();
        for (SmallRestaurant restaurant : getAllRestaurants()) {
            String restaurantName = restaurant.getName();
            difference = calculate(searchQuery, restaurantName);
            if (difference <= MAX_DIFFERENCE) {
                results.add(restaurant);
            }
        }
        Collections.reverse(results);

        // gib top ten 10
        if (results.size() > TOP_TEN) {
            ((ArrayList<SmallRestaurant>) results).subList(0, TOP_TEN);
        }

        return results;
    }

    /**
     * calculates the difference between 2 strings
     * 
     * @param x
     * @param y
     * @return
     */
    public static int calculate(String x, String y) {
        int[][] dp = new int[x.length() + 1][y.length() + 1];

        for (int i = 0; i <= x.length(); i++) {
            for (int j = 0; j <= y.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    dp[i][j] = min(dp[i - 1][j - 1]
                            + costOfSubstitution(x.charAt(i - 1), y.charAt(j - 1)),
                            dp[i - 1][j] + 1,
                            dp[i][j - 1] + 1);
                }
            }
        }

        return dp[x.length()][y.length()];
    }

    public static int min(int... numbers) {
        return Arrays.stream(numbers)
                .min().orElse(Integer.MAX_VALUE);
    }

    public static int costOfSubstitution(char a, char b) {
        if (a == b) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     *
     * @param id
     * @return
     */
    public static Reservation getReservation(int id) {
        List<Reservation> ret = new ArrayList<>();
        restaurants.stream().map(x -> x.getReservations()).forEach(x -> ret.addAll(x));

        if (ret.size() == 0) {
            return null;
        }
        return ret.get(0);
    }

    /**
     * Finds the restaurant in the list of restaurants and adds the new reservation
     * to it
     * 
     * @param reservation
     * @param visitor
     * @return
     */
    public static Reservation postReservation(Reservation reservation, Visitor visitor) {
        if (restaurants.stream().filter(res -> getRestaurantById(reservation.getId()).equals(res)).toList().get(0)
                .passReservation(reservation, visitor))
            return reservation;
        else {
            return null;
        }
    }

    /**
     *
     * @param id
     * @param secretCancelKey
     */
    public static void performActionOnReservation(int id, String actionSecretKey) {
        Reservation reservation = getReservation(id);
        if (reservation != null) {
            if (reservation.getCancelSecretKey().compareTo(actionSecretKey) == 0) {
                getRestaurantById(reservation.getId()).cancelReservation(reservation, actionSecretKey);
            } else if (reservation.getConfirmSecretKey().compareTo(actionSecretKey) == 0) {
                reservation.confirmReservation(actionSecretKey);
            }
        }
    }

    public static void addReview(int id, Review review) {
        if (getRestaurantById(id) != null) {
            getRestaurantById(id).addReview(review);
        }
    }
}
