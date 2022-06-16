package hello.world.demo.restaurant;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import org.apache.catalina.User;

import hello.world.demo.email.EmailServiceImpl;

public class Restaurant {

	private static final int RESERVATION_DURATION = 2;

	private int id;

	private String name;

	private String description;

	private Location location;

	private List<String> pictures;

	private List<Integer> ratings;

	private List<String> comments;

	private List<LocalTime> openingTimes;

	private List<LocalTime> closingTime;

	private String website;

	private String priceCategory;

	private List<Tisch> tables;

	private RestaurantType restaurantType;

	private List<Reservation> reservations;

	public Restaurant(String name, String description, Location location, List<String> pictures, List<Integer> ratings,
			List<String> comments, List<LocalTime> openingTimes, List<LocalTime> closingTime, String website,
			String priceCategory,
			List<Tisch> tables, RestaurantType restaurantType, List<Reservation> reservations) {
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
		this.tables = tables;
		this.restaurantType = restaurantType;
		this.reservations = reservations;
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

	public List<Integer> getRatings() {
		return ratings;
	}

	public void setRatings(List<Integer> ratings) {
		this.ratings = ratings;
	}

	public double getAverageRating() {
		double ret = 0;
		for (Integer i : ratings) {
			ret += i;
		}
		return ret / (double) ratings.size();
	}

	public List<String> getComments() {
		return comments;
	}

	public void setComments(List<String> comments) {
		this.comments = comments;
	}

	public List<LocalTime> getOpeningTimes() {
		return openingTimes;
	}

	public void setOpeningTimes(List<LocalTime> openingTimes) {
		this.openingTimes = openingTimes;
	}

	public List<LocalTime> getClosingTime() {
		return closingTime;
	}

	public void setClosingTime(List<LocalTime> closingTime) {
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

	public void setTables(List<Tisch> tables) {
		this.tables = tables;
	}

	public List<Tisch> getTables() {
		return tables;
	}

	public RestaurantType getRestaurantType() {
		return restaurantType;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void passReservation(Reservation reservation, Visitor user) {
		reservation.setUser(user);
		reservations.add(reservation);
	}

	public void cancelReservation(Reservation reservation, Visitor user) {
		EmailServiceImpl.confirmCancellation(reservation);
		reservation.setUser(user);
		reservations.remove(reservation);
	}

	public boolean checkAvailability(LocalTime from, LocalDate date, int persons) {
		List<Tisch> availableTables = this.tables.stream().filter(x -> x.getSeats() >= persons).toList();

		List<Reservation> possibleReservations = this.reservations.stream().filter(x -> x.getDate().equals(date))
				.filter(x -> Duration.between(x.getTime(), from).toHours() < RESERVATION_DURATION)
				.filter(x -> availableTables.stream().anyMatch(y -> y.getId() == x.getId())).toList();

		return availableTables.size() > possibleReservations.size();
	}

}
