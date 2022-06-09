package hello.world.demo.querys;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import hello.world.demo.restaurant.Restaurant;

public interface RestaurantRepo extends CrudRepository<Restaurant, Long> {
    Restaurant findById(long id);
    List<Restaurant> getAllRestaurants();
}