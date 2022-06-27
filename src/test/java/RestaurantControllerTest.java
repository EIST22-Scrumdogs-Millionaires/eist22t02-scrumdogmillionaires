import hello.world.demo.control.RestaurantOverview;
import hello.world.demo.model.Restaurant;
import hello.world.demo.model.RestaurantType;
import hello.world.demo.model.SmallRestaurant;
import org.junit.jupiter.api.Test;

import javax.el.MethodNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
    void testFilter() {
        List<SmallRestaurant> restaurants = RestaurantOverview.search("augustiner");

        List<SmallRestaurant> filteredRestaurants = RestaurantOverview.filter("augustiner", "T_BAVARIAN");

        for (SmallRestaurant restaurant : restaurants) {
            if (restaurant.getRestaurantType().equals(RestaurantType.BAVARIAN)) {
                assertTrue(filteredRestaurants.contains(restaurant));
            } else {
                assertFalse(filteredRestaurants.contains(restaurant));
            }
        }

    }

}
