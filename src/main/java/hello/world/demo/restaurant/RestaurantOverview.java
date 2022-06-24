package hello.world.demo.restaurant;

import hello.world.demo.Data;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class RestaurantOverview extends Thread {
    private static List<Restaurant> restaurants = Data.getRestaurants();

    private final static int MAX_LEVENSTHEIN_DIFFERENCE = 40;

    private static final int UPDATE_TIME = 100_000;

    // Deletes all unconfirmed Reservations 12 Hours before the reserved time
    @Override
    public void run() {
        while (true) {
            restaurants.stream().forEach(x -> {
                x.getReservations().stream()
                        .filter(y -> !y.getConfirmed() && (Duration.between(y.getDate(), LocalDate.now()).toHours()
                                + Duration.between(y.getTime(), LocalTime.now()).toHours() < 12))
                        .forEach(y -> x.cancelReservation(y, y.getCancelSecretKey()));
            });
            try {
                Thread.sleep(UPDATE_TIME);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }
    }

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
                x.getLocation(), x.getWebsite(), x.getPriceCategory(), x.averageRating(), x.getRestaurantType(),
                x.getPictures()))
                .toList();
    }

    public static List<SmallRestaurant> getTopTen() {
        return restaurants.stream().map(x -> new SmallRestaurant(x.getId(), x.getName(), x.getDescription(),
                x.getLocation(), x.getWebsite(), x.getPriceCategory(), x.averageRating(), x.getRestaurantType(),
                x.getPictures()))
                .sorted((a, b) -> (int) ((b.getAverageRating() * 1000d) - (a.getAverageRating() * 1000d))).limit(10)
                .toList();

    }

    /*
     * Filterype Syntax:
     * T_t: RestaurantType
     * P_p: Prce Category
     * D_x_y_d: Distance, x and y coordinates and distance in km respectively
     * A_r: Average Rating
     * F_f_d_p: Free time slots, timefrom, date and number of persons
     */
    public static List<SmallRestaurant> filter(String searchQuery, String filter) {
        List<String> filterTypes = Arrays.stream(filter.split("@")).filter(x -> !x.isBlank()).toList();
        List<Restaurant> ret;
        if (searchQuery.isBlank() || searchQuery.compareTo("all") == 0) {
            ret = restaurants;
        } else {
            ret = searchB(searchQuery);
        }
        System.out.println("Here");
        for (String filterType : filterTypes) {
            System.out.println(filterType);

            switch (filterType.charAt(0)) {
                case 'T': {
                    System.out.println();
                    RestaurantType restaurantType = RestaurantType.valueOf(getArgument(filterType, 0));
                    System.out.println(restaurantType);
                    ret = ret.stream().filter(x -> x.getRestaurantType() == restaurantType).toList();
                    break;
                }
                case 'P': {
                    int priceCategory = Integer.parseInt(getArgument(filterType, 0));
                    ret = ret.stream().filter(x -> x.getPriceCategory() == priceCategory).toList();
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
                    ret = ret.stream().filter(l -> l.averageRating() >= avg).toList();
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
                x.getLocation(), x.getWebsite(), x.getPriceCategory(), x.averageRating(), x.getRestaurantType(),
                null))
                .toList();

    }

    private static String getArgument(String src, int num) {
        return Arrays.stream(src.split("_")).filter(x -> !x.isBlank()).toList().get(num + 1);
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
        return restaurants.stream()
                .filter(x -> calculateLevenstheinDistance(searchQuery,
                        x.getName() + " " + x.getDescription()) <= MAX_LEVENSTHEIN_DIFFERENCE)
                .sorted((a, b) -> calculateLevenstheinDistance(searchQuery, a.getName() + " " + a.getDescription())
                        - calculateLevenstheinDistance(searchQuery, b.getName() + " " + b.getDescription()))
                .limit(10).toList();
    }

    public static List<SmallRestaurant> search(String searchQuery) {
        return getAllRestaurants().stream()
                .filter(x -> Math.min(calculateLevenstheinDistance(searchQuery,
                        x.getName()),
                        calculateLevenstheinDistance(searchQuery,
                                x.getDescription())) <= MAX_LEVENSTHEIN_DIFFERENCE)
                .sorted((a, b) -> Math.min(calculateLevenstheinDistance(searchQuery,
                        a.getName()),
                        calculateLevenstheinDistance(searchQuery,
                                a.getDescription()))
                        - Math.min(calculateLevenstheinDistance(searchQuery,
                                b.getName()),
                                calculateLevenstheinDistance(searchQuery,
                                        b.getDescription())))
                .limit(10).toList();
    }

    /**
     * calculates the difference between 2 strings
     * 
     * @param x
     * @param y
     * @return
     */
    private static int calculateLevenstheinDistance(String x, String y) {
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
    public static Reservation postReservation(Reservation reservation) {
        restaurants.stream().filter(res -> getRestaurantById(reservation.getId()).equals(res)).toList().get(0)
                .passReservation(reservation);
        Data.saveRestaurants(restaurants);
        return reservation;

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
            Data.saveRestaurants(restaurants);
        }
    }

    public static void addReview(int id, Review review) {
        if (getRestaurantById(id) != null) {
            getRestaurantById(id).addReview(review);
            Data.saveRestaurants(restaurants);
        }
    }

    public synchronized static List<Tisch> getAvailableTables(int restaurant_id, LocalDate date, LocalTime time,
            int seats) {
        Restaurant restaurant = getRestaurantById(restaurant_id);
        if (restaurant != null) {
            List<Tisch> available = getRestaurantById(restaurant_id).getAvailableTables(time, date, seats);
            return restaurant.getTables().stream().map(x -> {
                if (available.stream().anyMatch(y -> y.getId() == x.getId())) {
                    x.setAvailable(true);
                } else {
                    x.setAvailable(false);
                }
                return x;
            }).toList();
        }
        return null;
    }
}
