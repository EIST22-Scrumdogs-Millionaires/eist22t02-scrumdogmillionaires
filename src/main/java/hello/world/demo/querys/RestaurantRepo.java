package hello.world.demo.querys;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.world.demo.restaurant.Restaurant;

public interface RestaurantRepo extends JpaRepository<Restaurant, Long> {
    // @Query(value = "SELECT * FROM RESTAURANTS WHERE restaurant_id=?1", nativeQuery = true)
    // Restaurant findById(long id);
    
    // @Query(value = "SELECT * FROM RESTAURANTS", nativeQuery = true)
    // List<Restaurant> getAllRestaurants();
}