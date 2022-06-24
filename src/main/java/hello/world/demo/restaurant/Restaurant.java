package hello.world.demo.restaurant;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import hello.world.demo.Data;
import hello.world.demo.Util;

import hello.world.demo.email.EmailThread;

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

	private String openingAndClosingTimesAsFancyString;

	private String website;

	private int priceCategory;

	private double averageRating;

	private List<Tisch> tables;

	private RestaurantType restaurantType;

	private List<Reservation> reservations;

	public Restaurant() {
	}

	public Restaurant(int id, String name, String description, Location location, List<String> pictures,
			List<Review> reviews,
			List<LocalTime> openingTimes, List<LocalTime> closingTime, String website,
			int priceCategory,
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
		calculateOpeningAndClosingTimesAsFancyString();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		return this.averageRating;
	}

	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}

	public double averageRating() {
		if (reviews == null || reviews.size() == 0) {
			return 0;
		}
		double ret = 0;
		for (Review i : reviews) {
			if (i != null && i.getRating() != null)
				ret += i.getRating();
		}
		return ret / (double) reviews.size();
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public void addReview(Review review) {
		System.out.println("adding :" + review.getComment());
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

	public String OpeningAndClosingTimesAsFancyString() {
		calculateOpeningAndClosingTimesAsFancyString();
		return openingAndClosingTimesAsFancyString;

	}

	private void calculateOpeningAndClosingTimesAsFancyString() {
		if (openingTimes == null || closingTime == null || openingTimes.size() != 7 || closingTime.size() != 7) {
			return;
		}
		openingAndClosingTimesAsFancyString = "";
		List<Integer> done = new ArrayList<>();
		int actualDay = 0;
		while (done.size() != 7) {
			while (done.contains(actualDay) && actualDay <= 7) {
				actualDay++;
			}
			if (actualDay >= 7) {
				break;
			}
			String temp = getDay(actualDay);
			done.add(actualDay);
			int prev = actualDay;
			boolean first = true;
			// Restaurant not open?
			if (Duration.between(openingTimes.get(actualDay), closingTime.get(actualDay)).toHours() == 0) {
				continue;
			}
			for (int i = actualDay + 1; i < 7; i++) {
				if (Duration.between(openingTimes.get(actualDay), openingTimes.get(i)).toHours() == 0
						&& Duration.between(closingTime.get(actualDay), closingTime.get(i)).toHours() == 0) {
					done.add(i);
					if (i - 1 == prev) {
						if (first) {
							temp = temp.substring(0, temp.length()) + "-" + getDay(i);
							first = false;
						} else {
							temp = temp.substring(0, temp.length() - 3) + getDay(i);
						}
					} else {
						temp = temp.substring(0, temp.length()) + ", " + getDay(i);
						first = true;
					}
					prev = i;
				}
			}
			temp += ": " + openingTimes.get(actualDay).toString() + "-" + closingTime.get(actualDay).toString();
			openingAndClosingTimesAsFancyString += temp + "\n";
		}
		openingAndClosingTimesAsFancyString = openingAndClosingTimesAsFancyString.substring(0,
				openingAndClosingTimesAsFancyString.length() - 1);

	}

	private String getDay(int id) {
		return switch (id) {
			case 0 -> "Mon";
			case 1 -> "Tue";
			case 2 -> "Wed";
			case 3 -> "Thu";
			case 4 -> "Fri";
			case 5 -> "Sat";
			case 6 -> "Sun";
			default -> "Error 404";
		};
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public int getPriceCategory() {
		return priceCategory;
	}

	public void setPriceCategory(int priceCategory) {
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

	public void setRestaurantType(RestaurantType restaurantType) {
		this.restaurantType = restaurantType;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public void cancelReservation(Reservation reservation, String cancelSecretKey) {
		if (reservation.getCancelSecretKey().compareTo(cancelSecretKey) == 0) {
			reservations.remove(reservation);
		}
	}

	public boolean checkAvailability(LocalTime from, LocalDate date, int persons) {
		return getAvailableTables(from, date, persons).size() > 0;
	}

	public List<Tisch> getAvailableTables(LocalTime from, LocalDate date, int persons) {
		if (Duration.between(openingTimes.get(date.getDayOfWeek().getValue() - 1),
				closingTime.get(date.getDayOfWeek().getValue() - 1)).toHours() == 0) {
			return new ArrayList<>();
		}
		LocalTime closing = LocalTime.of(
				closingTime.get(date.getDayOfWeek().getValue() - 1).getHour(),
				closingTime.get(date.getDayOfWeek().getValue() - 1).getMinute());
		closing = closing.minusHours(RESERVATION_DURATION);

		if (from.isBefore(openingTimes.get(date.getDayOfWeek().getValue() - 1))
				|| from.isAfter(closing)) {
			return new ArrayList<>();
		}
		List<Tisch> availableTables = this.tables.stream().filter(x -> x.getSeats() >= persons).toList();

		List<Reservation> possibleReservations = this.reservations.stream().filter(x -> x.getDate().equals(date))
				.filter(x -> Duration.between(x.getTime(), from).toHours() < RESERVATION_DURATION)
				.filter(x -> availableTables.stream().anyMatch(y -> y.getId() == x.getId())).toList();

		return availableTables.stream()
				.filter(x -> !possibleReservations.stream().anyMatch(y -> y.getTable().getId() == x.getId())).toList();
	}

	public void passReservation(Reservation reservation) {

		String emailResText = Util.reservationMail(reservation.getUser(), reservation);
		String emailResConfirmText = Util.confirmMail(reservation.getUser(), reservation, this);

		Email emailRes = new Email(reservation.getUser().getEmail(), "Reservierung bestätigt", emailResText,
				LocalDate.now(),
				LocalTime.now());
		Email emailResConfirm = new Email(reservation.getUser().getEmail(), "Bitte bestätigen Sie Ihre Reservierung",
				emailResConfirmText,
				reservation.getDate().minusDays(1), reservation.getTime().minusHours(24));
		EmailThread.addEmail(emailRes);
		EmailThread.addEmail(emailResConfirm);
		reservation.setId(Data.getReservationId());
		Data.saveReservationId(reservation.getId() + 1);
		reservations.add(reservation);
	}
}
