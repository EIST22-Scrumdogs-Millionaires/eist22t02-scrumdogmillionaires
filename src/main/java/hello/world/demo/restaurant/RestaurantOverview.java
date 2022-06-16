package hello.world.demo.restaurant;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hello.world.demo.Data;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

	public static List<SmallRestaurant> getAllRestaurants() {
		return restaurants.stream().map(x -> new SmallRestaurant(x.getName(), x.getDescription(),
				x.getLocation(), x.getWebsite(), x.getPriceCategory(), x.getAverageRating())).toList();

	}

	public static List<SmallRestaurant> getTopTen() {
		return restaurants.stream().map(x -> new SmallRestaurant(x.getName(), x.getDescription(),
				x.getLocation(), x.getWebsite(), x.getPriceCategory(), x.getAverageRating()))
				.sorted((a, b) -> (int) ((a.getAverageRating() * 1000d) - (b.getAverageRating() * 1000d))).limit(10)
				.toList();

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

}
