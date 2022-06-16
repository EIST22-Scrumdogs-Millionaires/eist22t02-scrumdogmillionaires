package hello.world.demo.restaurant;

import java.time.LocalTime;
import java.util.*;

import javax.persistence.*;
import javax.persistence.Table;


import hello.world.demo.Data;
import hello.world.demo.EmailService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;

@Entity(name = "Restaurant")
@Table(name = "restaurant")
public class Restaurant {
	private final static int MAX_DIFFERENCE = 30;
	private final static int TOP_TEN = 10;

	@Id
   @GeneratedValue(strategy=GenerationType.AUTO)
   @Column(name="restaurant_id")
	private int id;

	@Column(name="name")
    private String name;
	@Column(name="description")
    private String description;
	
	@Column(name="location")
	private Location location;
	@ElementCollection
	@Column(name="pictures")
    private List<String> pictures;
	@ElementCollection
	@Column(name="ratings")
    private List<String> ratings;
	@ElementCollection
	@Column(name="comments")
    private List<String> comments;
	@Column(name="openingTimes")
    private LocalTime openingTimes;
	@Column(name="closingTime")
    private LocalTime closingTime;
	@Column(name="website")
    private String website;
	@Column(name="priceCategory")
    private String priceCategory;
	@ElementCollection
	@Column(name="comments")
	private List<Table> tables;


	@Autowired
    public Restaurant(String name, String description, Location location, List<String> pictures, List<String> ratings,
			List<String> comments, LocalTime openingTimes, LocalTime closingTime, String website, String priceCategory, List<Table> tables) {
		super();
		this.name = name;
		this.description = description;
		this.location = location;
		this.pictures = pictures;
		this.ratings = ratings;
		this.comments = comments;
		this.openingTimes = openingTimes;
		this.closingTime = closingTime;
		this.website = website;
		this.priceCategory = priceCategory;
		this.tables=tables;

	}
	@Autowired
	public Restaurant() {

	}

	public int getId() {
		return id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public List<String> getPictures() {
		return pictures;
	}

	public void setPictures(List<String> pictures) {
		this.pictures = pictures;
	}

	public List<String> getRatings() {
		return ratings;
	}

	public void setRatings(List<String> ratings) {
		this.ratings = ratings;
	}

	public List<String> getComments() {
		return comments;
	}

	public void setComments(List<String> comments) {
		this.comments = comments;
	}

	public LocalTime getOpeningTimes() {
		return openingTimes;
	}

	public void setOpeningTimes(LocalTime openingTimes) {
		this.openingTimes = openingTimes;
	}

	public LocalTime getClosingTime() {
		return closingTime;
	}

	public void setClosingTime(LocalTime closingTime) {
		this.closingTime = closingTime;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getPriceCategory() {
		return priceCategory;
	}

	public void setPriceCategory(String priceCategory) {
		this.priceCategory = priceCategory;
	}

	public void setTables(List<Table> tables) {
		this.tables = tables;
	}

	public List<Table> getTables() {
		return tables;
	}

	public void passReservation(Reservation reservation, User user) {
	}

	public void cancelReservation(Reservation reservation, User user){
		EmailService.confirmCancellation(reservation);
	}

	public void getDetail() {
	}

	public void checkAvailability(LocalTime date) {
	}

	public List<SmallRestaurant> search(String searchQuery) {
		int difference = 0;
		List<SmallRestaurant> results = new ArrayList<>();
		for (SmallRestaurant restaurant : Data.getAllRestaurants()) {
			String restaurantName = restaurant.getName();
			difference = calculate(searchQuery,restaurantName);
			if(difference <= MAX_DIFFERENCE) {
				results.add(restaurant);
			}
		}
		Collections.reverse(results);

		//gib top ten 10
		if (results.size() > TOP_TEN) {
			((ArrayList<SmallRestaurant>) results).trimToSize();
			}

		return results;

	}

	public static int calculate(String x, String y) {
		int[][] dp = new int[x.length() + 1][y.length() + 1];

		for (int i = 0; i <= x.length(); i++) {
			for (int j = 0; j <= y.length(); j++) {
				if (i == 0) {
					dp[i][j] = j;
				}
				else if (j == 0) {
					dp[i][j] = i;
				}
				else {
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
		if(a==b) {
			return 0;
		} else {
			return 1;
		}
	}



}
