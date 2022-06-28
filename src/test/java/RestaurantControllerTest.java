import hello.world.demo.control.Data;
import hello.world.demo.control.RestaurantOverview;
import hello.world.demo.model.Restaurant;
import hello.world.demo.model.RestaurantType;
import hello.world.demo.model.SmallRestaurant;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class RestaurantControllerTest {

    @Test
    void testDataIntegrity() {
        List<SmallRestaurant> restaurants = RestaurantOverview.getAllRestaurants();
        assertEquals(50, restaurants.size());

        for (SmallRestaurant restaurant : restaurants) {
            assertNotNull(restaurant.getRestaurantType());
            assertNotNull(restaurant.getPictures());
            assertNotNull(restaurant.getLocation());
            assertNotNull(restaurant.getName());
            assertNotNull(restaurant.getDescription());
            assertNotNull(restaurant.getWebsite());
            assertNotEquals(0, restaurant.getAverageRating());
            assertNotEquals(0, restaurant.getPriceCategory());
        }
    }

    @Test
    void testGetRestaurantByIdValid() {
        Restaurant restaurant = RestaurantOverview.getRestaurantById(1);
        assertNotNull(restaurant);
        assertEquals(1, restaurant.getId());
    }

    @Test
    void testGetRestaurantByIdInvalid() {
        assertNull(RestaurantOverview.getRestaurantById(-1));
    }

    @Test
    void testGetTopTen() {
        List<SmallRestaurant> restaurants = RestaurantOverview.getTopTen();
        assertEquals(10, restaurants.size());

        double rating = findMaxRating(RestaurantOverview.getAllRestaurants());
        for (SmallRestaurant restaurant : restaurants) {
            assertTrue(restaurant.getAverageRating() <= rating);
            rating = restaurant.getAverageRating();
        }
    }

    private double findMaxRating(List<SmallRestaurant> restaurants) {
        double maxRating = 0;
        for (SmallRestaurant restaurant : restaurants) {
            if (restaurant.getAverageRating() > maxRating) {
                maxRating = restaurant.getAverageRating();
            }
        }
        return maxRating;
    }

    @Test
    void testLevenstheinDistance() {
        try {
            Method method = RestaurantOverview.class.getDeclaredMethod("calculateLevenstheinDistance", String.class, String.class);
            method.setAccessible(true);
            assertEquals(0, method.invoke(null, "abc", "abc"));
            assertEquals(1, method.invoke(null, "abc", "abd"));
            assertEquals(7, method.invoke(null, "augustiner", "augustiner-keller"));
            assertEquals(8, method.invoke(null, "augustiner", "L'Osteria"));
            assertEquals(7, method.invoke(null, "augustiner", "Augustiner am Dom"));
        } catch (NoSuchMethodException e) {
            fail("MethodNotFound");
        } catch (IllegalAccessException e) {
            fail("IllegalAccess");
        } catch (InvocationTargetException e) {
            fail("InvocationTarget");
        }
    }

    @Test
    void testFilterType() {
        List<SmallRestaurant> restaurants = RestaurantOverview.search("augustiner");
        List<SmallRestaurant> filteredRestaurants = RestaurantOverview.filter("augustiner", "T_BAVARIAN",0);

        for (SmallRestaurant restaurant : restaurants) {
            if (restaurant.getRestaurantType().equals(RestaurantType.BAVARIAN)) {
                assertTrue(filteredRestaurants.contains(restaurant));
            } else {
                assertFalse(filteredRestaurants.contains(restaurant));
            }
        }
        for (SmallRestaurant restaurant : filteredRestaurants) {
            assertTrue(restaurant.getRestaurantType().equals(RestaurantType.BAVARIAN));
        }
    }

    @Test
    void testFilterPrice() {
        List<SmallRestaurant> restaurants = RestaurantOverview.search("cafe");
        List<SmallRestaurant> filteredRestaurants = RestaurantOverview.filter("cafe", "P_3",0);

        for (SmallRestaurant restaurant : restaurants) {
            if (restaurant.getPriceCategory() == 3) {
                assertTrue(filteredRestaurants.contains(restaurant));
            } else {
                assertFalse(filteredRestaurants.contains(restaurant));
            }
        }
        for (SmallRestaurant restaurant : filteredRestaurants) {
            assertEquals(3, restaurant.getPriceCategory());
        }
    }

    @Test
    void testFilterRating() {
        List<SmallRestaurant> restaurants = RestaurantOverview.search("cafe");
        List<SmallRestaurant> filteredRestaurants = RestaurantOverview.filter("cafe", "A_3",0);

        for (SmallRestaurant restaurant : restaurants) {
            if (restaurant.getAverageRating() >= 3.0) {
                assertTrue(filteredRestaurants.contains(restaurant));
            } else {
                assertFalse(filteredRestaurants.contains(restaurant));
            }
        }
        for (SmallRestaurant restaurant : filteredRestaurants) {
            assertTrue(restaurant.getAverageRating() >= 3.0);
        }
    }

    @Test
    void testFilterTime() {
        List<SmallRestaurant> smallRestaurants = RestaurantOverview.search("cafe");
        List<SmallRestaurant> filteredRestaurants = RestaurantOverview.filter("cafe", "F_18:00_2022-07-07_2",0);
        List<Restaurant> restaurants = Data.getRestaurants();

        //The date is a thursday, so we need the third index in the times list
        for (SmallRestaurant restaurant : smallRestaurants) {
            restaurants.stream().filter(r -> r.getId() == restaurant.getId()).forEach(r -> {
                if (r.getOpeningTimes().get(3).getHour() <= 18 && r.getClosingTime().get(3).getHour() >= 18) {
                    assertTrue(filteredRestaurants.contains(restaurant));
                } else {
                    assertFalse(filteredRestaurants.contains(restaurant));
                }
            });
        }
    }

    @Test
    void testFilterDistance() {
        List<SmallRestaurant> smallRestaurants = RestaurantOverview.search("cafe");
        List<SmallRestaurant> filteredRestaurants = RestaurantOverview.filter("cafe", "D_11.64615674133299_48.24755548611005_1",0);

        List<Integer> expectedIds = Arrays.asList(new Integer[]{41,42,44,45});

        for (SmallRestaurant restaurant : smallRestaurants) {
            if (expectedIds.contains(restaurant.getId())) {
                assertTrue(filteredRestaurants.contains(restaurant));
            } else {
                assertFalse(filteredRestaurants.contains(restaurant));
            }
        }
    }

    @Test
    void testDistance() {
        //Arabesk Restaurant
        double lat1 = 48.1548167;
        double lng1 = 11.5856458;

        //CurLoc
        double lat2 = 48.1413069883512;
        double lng2 = 11.56174225921629;

        assertFalse(RestaurantOverview.distance(lat1, lat2, lng1, lng2, 2));
        assertTrue(RestaurantOverview.distance(lat1, lat2, lng1, lng2, 2.4));
    }

    @Test
    void testDistanceSame() {
        assertTrue(RestaurantOverview.distance(48.1548167, 48.1548167, 11.5856458, 11.5856458, 0.01));
    }

}
