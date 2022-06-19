package hello.world.demo.restaurant;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import hello.world.demo.email.EmailServiceImpl;
import hello.world.demo.email.EmailThread;
import org.apache.tomcat.jni.Local;

public class Restaurant {

	private static final int RESERVATION_DURATION = 2;

	private int id;

	private String name;

	private String description;

	private Location location;

	private List<String> pictures;

	private List<Review> reviews;

	private List<LocalTime> openingTimes;

	private List<LocalTime> closingTime;

	private String website;

	private String priceCategory;

	private List<Tisch> tables;

	private RestaurantType restaurantType;

	private List<Reservation> reservations;

	public Restaurant(int id, String name, String description, Location location, List<String> pictures,
			List<Review> reviews,
			List<LocalTime> openingTimes, List<LocalTime> closingTime, String website,
			String priceCategory,
			List<Tisch> tables, RestaurantType restaurantType, List<Reservation> reservations) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.location = location;
		this.pictures = pictures;
		this.reviews = reviews;
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

	public double getAverageRating() {
		if (reviews == null) {
			return 0;
		}
		double ret = 0;
		for (Review i : reviews) {
			ret += i.getRating();
		}
		return ret / (double) reviews.size();
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void addReview(Review review) {
		reviews.add(review);
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

	public boolean passReservation(Reservation reservation, Visitor user) {
		String emailResConfirmText = " Ihre Reservierung ist bestätigt, " + user.getUsername() + "!\n Vielen Dank dass Sie bei " + this.name + "reserviert haben. \n Tisch " + reservation.getTable().getId() + " für" + reservation.getTable().getSeats() + " Person(en) \n" + reservation.getDate() + " um " + reservation.getTime() + ". \n" + "Reservierungsname: " + reservation.getUser().getUsername() + "\n Bestätigungsnummer: " + reservation.getId() + "\n\n" + "Wir freuen uns auf Sie!" + "\n\n" + "Doch schon etwas Anderes vor? Sie können Ihre Reservierung bis zu 12h vorher stornieren, indem Sie auf den folgenden Link klicken. Es ist kinderleicht. Jetzt Reservierung stornieren:  localhost:8080/reservations/" + reservation.getId() + "/" + reservation.getCancelSecretKey() + " \n\n Was Sie vor Ihrem Besuch wissen sollten\n" +
				"Der Tisch wird bis zu 15 Minuten nach Ihrer Reservierungszeit für Sie freigehalten. Bitte rufen Sie uns an, wenn Sie sich um mehr als 15 Minuten verspäten.\n" +
				"Der Tisch wird 1 Stunde 30 Minuten für Gruppen von bis zu 4 Personen, 2 Stunden für bis zu 6 Personen und 2 Stunden 30 Minuten für Gruppen von mehr als 7 Personen reserviert.";
		Email emailResConfirm = new Email(user.getEmail(), "Reservierung bestätigt", emailResConfirmText, LocalDate.now(), LocalTime.now());
		EmailThread.addEmail(emailResConfirm);
		EmailThread.addEmail(emailResConfirm);
		reservation.setUser(user);
		reservations.add(reservation);
		return true;
	}

	public void cancelReservation(Reservation reservation, String cancelSecretKey) {
		if (reservation.getCancelSecretKey().compareTo(cancelSecretKey) == 0) {
			EmailServiceImpl.confirmCancellation(reservation);
			reservations.remove(reservation);
		}
	}

	public boolean checkAvailability(LocalTime from, LocalDate date, int persons) {
		List<Tisch> availableTables = this.tables.stream().filter(x -> x.getSeats() >= persons).toList();

		List<Reservation> possibleReservations = this.reservations.stream().filter(x -> x.getDate().equals(date))
				.filter(x -> Duration.between(x.getTime(), from).toHours() < RESERVATION_DURATION)
				.filter(x -> availableTables.stream().anyMatch(y -> y.getId() == x.getId())).toList();

		return availableTables.size() > possibleReservations.size();
	}

}
