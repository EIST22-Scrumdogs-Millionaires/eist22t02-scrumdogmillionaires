package hello.world.demo.restaurant;

import java.util.List;

public class SmallRestaurant {

	private int id;

	private String name;

	private String description;

	private Location location;

	private String website;

	private String priceCategory;

	private double averageRating;

	private RestaurantType restaurantType;

	private List<String> pictures;

	/**
	 * This model of a restaurant has less data and is used when the restaurant is
	 * visible in the overview
	 * 
	 * @param name
	 * @param description
	 * @param location
	 * @param website
	 * @param priceCategory
	 * @param averageRating
	 * @param restaurantType
	 */
	public SmallRestaurant(int id, String name, String description, Location location,
			String website, String priceCategory, double averageRating, RestaurantType restaurantType,
			List<String> pictures) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.location = location;
		this.website = website;
		this.priceCategory = priceCategory;
		this.averageRating = averageRating;
		this.restaurantType = restaurantType;
		this.pictures = pictures;

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

	public double getAverageRating() {
		return averageRating;
	}

	public RestaurantType getRestaurantType() {
		return restaurantType;
	}

	public List<String> getPictures() {
		return pictures;
	}

}
