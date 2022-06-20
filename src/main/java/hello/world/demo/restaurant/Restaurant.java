package hello.world.demo.restaurant;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAmount;
import java.util.*;

import hello.world.demo.email.EmailServiceImpl;

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
		calculateOpeningAndClosingTimesAsFancyString();
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

	public String getOpeningAndClosingTimesAsFancyString() {
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

	public void cancelReservation(Reservation reservation, String cancelSecretKey) {
		if (reservation.getCancelSecretKey().compareTo(cancelSecretKey) == 0) {
			EmailServiceImpl.confirmCancellation(reservation);
			reservations.remove(reservation);
		}
	}

	public boolean checkAvailability(LocalTime from, LocalDate date, int persons) {
		return getAvailableTables(from, date, persons).size() > 0;
	}

	public List<Tisch> getAvailableTables(LocalTime from, LocalDate date, int persons) {
		if (from.isBefore(openingTimes.get(date.getDayOfWeek().getValue() - 1))
				|| from.isAfter(LocalTime.of(
						closingTime.get(date.getDayOfWeek().getValue() - 1).getHour() - RESERVATION_DURATION,
						closingTime.get(date.getDayOfWeek().getValue() - 1).getMinute()))) {
			return new ArrayList<>();
		}
		List<Tisch> availableTables = this.tables.stream().filter(x -> x.getSeats() >= persons).toList();

		List<Reservation> possibleReservations = this.reservations.stream().filter(x -> x.getDate().equals(date))
				.filter(x -> Duration.between(x.getTime(), from).toHours() < RESERVATION_DURATION)
				.filter(x -> availableTables.stream().anyMatch(y -> y.getId() == x.getId())).toList();

		return availableTables.stream()
				.filter(x -> !possibleReservations.stream().anyMatch(y -> y.getTable().getId() == x.getId())).toList();
	}

	public void passReservation(Reservation reservation, Visitor user) {

		String emailResConfirmText = " Ihre Reservierung ist bestätigt, " + user.getUsername()
				+ "!\n Vielen Dank dass Sie bei " + this.name + "reserviert haben. \n Tisch "
				+ reservation.getTable().getId() + " für" + reservation.getTable().getSeats() + " Person(en) \n"
				+ reservation.getDate() + " um " + reservation.getTime() + ". \n" + "Reservierungsname: "
				+ reservation.getUser().getUsername() + "\n Bestätigungsnummer: " + reservation.getId() + "\n\n"
				+ "Wir freuen uns auf Sie!" + "\n\n"
				+ "Doch schon etwas Anderes vor? Sie können Ihre Reservierung bis zu 12h vorher stornieren, indem Sie auf den folgenden Link klicken. Es ist kinderleicht. Jetzt Reservierung stornieren:  localhost:8080/reservations/"
				+ reservation.getId() + "/" + reservation.getCancelSecretKey()
				+ " \n\n Was Sie vor Ihrem Besuch wissen sollten\n" +
				"Der Tisch wird bis zu 15 Minuten nach Ihrer Reservierungszeit für Sie freigehalten. Bitte rufen Sie uns an, wenn Sie sich um mehr als 15 Minuten verspäten.\n"
				+
				"Der Tisch wird 2 Stunden für Sie reserviert.";
		String emailres = "Bitte bestätigen Sie Ihre Reservierung, " + user.getUsername() + "\n Tisch "
				+ reservation.getTable().getId() + " für " + reservation.getTable().getSeats() + " Person(en) \n"
				+ reservation.getDate() + " um " + reservation.getTime() + ". \n" + "Reservierungsname:"
				+ reservation.getUser().getUsername() + "\n Bestätigungsnummer: " + reservation.getId() + "\n"
				+ "Bitte bestätigen Sie Ihre Reservierung, indem Sie auf den folgenden Link klicken: LINK EINFÜGEN"
				+ "\n Wir freuen uns auf Sie!" + "\n\n"
				+ "Doch schon etwas Anderes vor? Sie können Ihre Reservierung bis zu 12h vorher stornieren, indem Sie auf den folgenden Link klicken. Es ist kinderleicht. Jetzt Reservierung stornieren:  localhost:8080/reservations/"
				+ reservation.getId() + "/" + reservation.getCancelSecretKey();
		Email email = new Email(user.getEmail(), "Bitte bestätigen Sie Ihre Reservierung",
				"<!DOCTYPE HTML PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional //EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n"
						+
						"<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n"
						+
						"<head>\n" +
						"<!--[if gte mso 9]>\n" +
						"<xml>\n" +
						"  <o:OfficeDocumentSettings>\n" +
						"    <o:AllowPNG/>\n" +
						"    <o:PixelsPerInch>96</o:PixelsPerInch>\n" +
						"  </o:OfficeDocumentSettings>\n" +
						"</xml>\n" +
						"<![endif]-->\n" +
						"  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
						"  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
						"  <meta name=\"x-apple-disable-message-reformatting\">\n" +
						"  <!--[if !mso]><!--><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><!--<![endif]-->\n"
						+
						"  <title></title>\n" +
						"  \n" +
						"    <style type=\"text/css\">\n" +
						"      @media only screen and (min-width: 670px) {\n" +
						"  .u-row {\n" +
						"    width: 650px !important;\n" +
						"  }\n" +
						"  .u-row .u-col {\n" +
						"    vertical-align: top;\n" +
						"  }\n" +
						"\n" +
						"  .u-row .u-col-100 {\n" +
						"    width: 650px !important;\n" +
						"  }\n" +
						"\n" +
						"}\n" +
						"\n" +
						"@media (max-width: 670px) {\n" +
						"  .u-row-container {\n" +
						"    max-width: 100% !important;\n" +
						"    padding-left: 0px !important;\n" +
						"    padding-right: 0px !important;\n" +
						"  }\n" +
						"  .u-row .u-col {\n" +
						"    min-width: 320px !important;\n" +
						"    max-width: 100% !important;\n" +
						"    display: block !important;\n" +
						"  }\n" +
						"  .u-row {\n" +
						"    width: calc(100% - 40px) !important;\n" +
						"  }\n" +
						"  .u-col {\n" +
						"    width: 100% !important;\n" +
						"  }\n" +
						"  .u-col > div {\n" +
						"    margin: 0 auto;\n" +
						"  }\n" +
						"  }\n" +
						"body {\n" +
						"  margin: 0;\n" +
						"  padding: 0;\n" +
						"}\n" +
						"\n" +
						"table,\n" +
						"tr,\n" +
						"td {\n" +
						"  vertical-align: top;\n" +
						"  border-collapse: collapse;\n" +
						"}\n" +
						"\n" +
						"p {\n" +
						"  margin: 0;\n" +
						"}\n" +
						"\n" +
						".ie-container table,\n" +
						".mso-container table {\n" +
						"  table-layout: fixed;\n" +
						"}\n" +
						"\n" +
						"* {\n" +
						"  line-height: inherit;\n" +
						"}\n" +
						"\n" +
						"a[x-apple-data-detectors='true'] {\n" +
						"  color: inherit !important;\n" +
						"  text-decoration: none !important;\n" +
						"}\n" +
						"\n" +
						"table, td { color: #000000; } a { color: #0000ee; text-decoration: underline; } @media (max-width: 480px) { #u_content_image_6 .v-src-width { width: auto !important; } #u_content_image_6 .v-src-max-width { max-width: 30% !important; } #u_content_button_4 .v-size-width { width: 77% !important; } }\n"
						+
						"    </style>\n" +
						"  \n" +
						"  \n" +
						"\n" +
						"<!--[if !mso]><!--><link href=\"https://fonts.googleapis.com/css?family=Open+Sans:400,700&display=swap\" rel=\"stylesheet\" type=\"text/css\"><link href=\"https://fonts.googleapis.com/css?family=Raleway:400,700&display=swap\" rel=\"stylesheet\" type=\"text/css\"><!--<![endif]-->\n"
						+
						"\n" +
						"</head>\n" +
						"\n" +
						"<body class=\"clean-body u_body\" style=\"margin: 0;padding: 0;-webkit-text-size-adjust: 100%;background-color: #a9f8c9;color: #000000\">\n"
						+
						"  <!--[if IE]><div class=\"ie-container\"><![endif]-->\n" +
						"  <!--[if mso]><div class=\"mso-container\"><![endif]-->\n" +
						"  <table style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;min-width: 320px;Margin: 0 auto;background-color: #a9f8c9;width:100%\" cellpadding=\"0\" cellspacing=\"0\">\n"
						+
						"  <tbody>\n" +
						"  <tr style=\"vertical-align: top\">\n" +
						"    <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\n"
						+
						"    <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td align=\"center\" style=\"background-color: #a9f8c9;\"><![endif]-->\n"
						+
						"    \n" +
						"\n" +
						"<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n" +
						"  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 650px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\">\n"
						+
						"    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\n"
						+
						"      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:650px;\"><tr style=\"background-color: transparent;\"><![endif]-->\n"
						+
						"      \n" +
						"<!--[if (mso)|(IE)]><td align=\"center\" width=\"650\" style=\"width: 650px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n"
						+
						"<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 650px;display: table-cell;vertical-align: top;\">\n"
						+
						"  <div style=\"width: 100% !important;\">\n" +
						"  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\"><!--<![endif]-->\n"
						+
						"  \n" +
						"<table id=\"u_content_image_6\" style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
						+
						"  <tbody>\n" +
						"    <tr>\n" +
						"      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:20px 10px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n"
						+
						"        \n" +
						"<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
						"  <tr>\n" +
						"    <td style=\"padding-right: 0px;padding-left: 0px;\" align=\"center\">\n" +
						"      \n" +
						"      <img align=\"center\" border=\"0\" src=\"https://cdn.templates.unlayer.com/assets/1639409232431-1.png\" alt=\"Logo\" title=\"Logo\" style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: inline-block !important;border: none;height: auto;float: none;width: 22%;max-width: 138.6px;\" width=\"138.6\" class=\"v-src-width v-src-max-width\"/>\n"
						+
						"      \n" +
						"    </td>\n" +
						"  </tr>\n" +
						"</table>\n" +
						"\n" +
						"      </td>\n" +
						"    </tr>\n" +
						"  </tbody>\n" +
						"</table>\n" +
						"\n" +
						"<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
						+
						"  <tbody>\n" +
						"    <tr>\n" +
						"      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:0px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n"
						+
						"        \n" +
						"<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
						"  <tr>\n" +
						"    <td style=\"padding-right: 0px;padding-left: 0px;\" align=\"center\">\n" +
						"      \n" +
						"      <img align=\"center\" border=\"0\" src=\"https://cdn.templates.unlayer.com/assets/1637562164357-jghj.png\" alt=\"border\" title=\"border\" style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: inline-block !important;border: none;height: auto;float: none;width: 100%;max-width: 650px;\" width=\"650\" class=\"v-src-width v-src-max-width\"/>\n"
						+
						"      \n" +
						"    </td>\n" +
						"  </tr>\n" +
						"</table>\n" +
						"\n" +
						"      </td>\n" +
						"    </tr>\n" +
						"  </tbody>\n" +
						"</table>\n" +
						"\n" +
						"  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n" +
						"  </div>\n" +
						"</div>\n" +
						"<!--[if (mso)|(IE)]></td><![endif]-->\n" +
						"      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
						"    </div>\n" +
						"  </div>\n" +
						"</div>\n" +
						"\n" +
						"\n" +
						"\n" +
						"<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n" +
						"  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 650px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #f1f3f5;\">\n"
						+
						"    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\n"
						+
						"      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:650px;\"><tr style=\"background-color: #f1f3f5;\"><![endif]-->\n"
						+
						"      \n" +
						"<!--[if (mso)|(IE)]><td align=\"center\" width=\"650\" style=\"width: 650px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\" valign=\"top\"><![endif]-->\n"
						+
						"<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 650px;display: table-cell;vertical-align: top;\">\n"
						+
						"  <div style=\"width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\n"
						+
						"  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\"><!--<![endif]-->\n"
						+
						"  \n" +
						"<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
						+
						"  <tbody>\n" +
						"    <tr>\n" +
						"      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:0px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n"
						+
						"        \n" +
						"<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
						"  <tr>\n" +
						"    <td style=\"padding-right: 0px;padding-left: 0px;\" align=\"center\">\n" +
						"      \n" +
						"      <img align=\"center\" border=\"0\" src=\"https://images.unlayer.com/projects/0/1655633097642-ratskeller-restaurant-kleiner.jpg\" alt=\"Hero Image\" title=\"Hero Image\" style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: inline-block !important;border: none;height: auto;float: none;width: 77%;max-width: 500.5px;\" width=\"500.5\" class=\"v-src-width v-src-max-width\"/>\n"
						+
						"      \n" +
						"    </td>\n" +
						"  </tr>\n" +
						"</table>\n" +
						"\n" +
						"      </td>\n" +
						"    </tr>\n" +
						"  </tbody>\n" +
						"</table>\n" +
						"\n" +
						"  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n" +
						"  </div>\n" +
						"</div>\n" +
						"<!--[if (mso)|(IE)]></td><![endif]-->\n" +
						"      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
						"    </div>\n" +
						"  </div>\n" +
						"</div>\n" +
						"\n" +
						"\n" +
						"\n" +
						"<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n" +
						"  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 650px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #f1f3f5;\">\n"
						+
						"    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\n"
						+
						"      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:650px;\"><tr style=\"background-color: #f1f3f5;\"><![endif]-->\n"
						+
						"      \n" +
						"<!--[if (mso)|(IE)]><td align=\"center\" width=\"650\" style=\"width: 650px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\" valign=\"top\"><![endif]-->\n"
						+
						"<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 650px;display: table-cell;vertical-align: top;\">\n"
						+
						"  <div style=\"width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\n"
						+
						"  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\"><!--<![endif]-->\n"
						+
						"  \n" +
						"<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
						+
						"  <tbody>\n" +
						"    <tr>\n" +
						"      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:40px 10px 10px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n"
						+
						"        \n" +
						"  <h1 style=\"margin: 0px; color: #333333; line-height: 140%; text-align: center; word-wrap: break-word; font-weight: normal; font-family: 'Open Sans',sans-serif; font-size: 30px;\">\n"
						+
						"    <strong>Ihre Reservierung</strong>\n" +
						"  </h1>\n" +
						"\n" +
						"      </td>\n" +
						"    </tr>\n" +
						"  </tbody>\n" +
						"</table>\n" +
						"\n" +
						"<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
						+
						"  <tbody>\n" +
						"    <tr>\n" +
						"      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 50px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n"
						+
						"        \n" +
						"  <div style=\"color: #5e5e5e; line-height: 170%; text-align: center; word-wrap: break-word;\">\n"
						+
						"    <p style=\"font-size: 14px; line-height: 170%;\"><span style=\"font-family: Raleway, sans-serif; font-size: 16px; line-height: 27.2px;\">Vielen Dank dass Sie bei {{}} reserviert haben. </span></p>\n"
						+
						"<p style=\"font-size: 14px; line-height: 170%;\"><span style=\"font-family: Raleway, sans-serif; font-size: 16px; line-height: 27.2px;\">Tisch {{}} &nbsp;am {{}} um {{}}<br />Reservierungsname: {{user.getUserName()}}<br />Best&auml;tigungsnummer: {{}}</span></p>\n"
						+
						"  </div>\n" +
						"\n" +
						"      </td>\n" +
						"    </tr>\n" +
						"  </tbody>\n" +
						"</table>\n" +
						"\n" +
						"<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
						+
						"  <tbody>\n" +
						"    <tr>\n" +
						"      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 10px 30px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n"
						+
						"        \n" +
						"  <h1 style=\"margin: 0px; color: #722b1d; line-height: 140%; text-align: center; word-wrap: break-word; font-weight: normal; font-family: 'Open Sans',sans-serif; font-size: 25px;\">\n"
						+
						"    Bitte best&auml;tigen Sie jetzt ihre Reservierung:\n" +
						"  </h1>\n" +
						"\n" +
						"      </td>\n" +
						"    </tr>\n" +
						"  </tbody>\n" +
						"</table>\n" +
						"\n" +
						"  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n" +
						"  </div>\n" +
						"</div>\n" +
						"<!--[if (mso)|(IE)]></td><![endif]-->\n" +
						"      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
						"    </div>\n" +
						"  </div>\n" +
						"</div>\n" +
						"\n" +
						"\n" +
						"\n" +
						"<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n" +
						"  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 650px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #f1f3f5;\">\n"
						+
						"    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\n"
						+
						"      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:650px;\"><tr style=\"background-color: #f1f3f5;\"><![endif]-->\n"
						+
						"      \n" +
						"<!--[if (mso)|(IE)]><td align=\"center\" width=\"650\" style=\"width: 650px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\" valign=\"top\"><![endif]-->\n"
						+
						"<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 650px;display: table-cell;vertical-align: top;\">\n"
						+
						"  <div style=\"width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\n"
						+
						"  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\"><!--<![endif]-->\n"
						+
						"  \n" +
						"<table id=\"u_content_button_4\" style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
						+
						"  <tbody>\n" +
						"    <tr>\n" +
						"      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 10px 60px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n"
						+
						"        \n" +
						"<div align=\"center\">\n" +
						"  <!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-spacing: 0; border-collapse: collapse; mso-table-lspace:0pt; mso-table-rspace:0pt;font-family:arial,helvetica,sans-serif;\"><tr><td style=\"font-family:arial,helvetica,sans-serif;\" align=\"center\"><v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" href=\"https://unlayer.com\" style=\"height:64px; v-text-anchor:middle; width:290px;\" arcsize=\"6.5%\" stroke=\"f\" fillcolor=\"#722b1d\"><w:anchorlock/><center style=\"color:#ffffff;font-family:arial,helvetica,sans-serif;\"><![endif]-->\n"
						+
						"    <a href=\"https://unlayer.com\" target=\"_blank\" class=\"v-size-width\" style=\"box-sizing: border-box;display: inline-block;font-family:arial,helvetica,sans-serif;text-decoration: none;-webkit-text-size-adjust: none;text-align: center;color: #ffffff; background-color: #722b1d; border-radius: 4px;-webkit-border-radius: 4px; -moz-border-radius: 4px; width:46%; max-width:100%; overflow-wrap: break-word; word-break: break-word; word-wrap:break-word; mso-border-alt: none;border-top-color: #000000; border-top-style: solid; border-top-width: 0px; border-left-color: #000000; border-left-style: solid; border-left-width: 0px; border-right-color: #000000; border-right-style: solid; border-right-width: 0px; border-bottom-color: #000000; border-bottom-style: solid; border-bottom-width: 0px;\">\n"
						+
						"      <span style=\"display:block;padding:20px;line-height:120%;\"><span style=\"font-size: 20px; line-height: 24px; font-family: 'Open Sans', sans-serif;\">Reservierung best&auml;tigen</span></span>\n"
						+
						"    </a>\n" +
						"  <!--[if mso]></center></v:roundrect></td></tr></table><![endif]-->\n" +
						"</div>\n" +
						"\n" +
						"      </td>\n" +
						"    </tr>\n" +
						"  </tbody>\n" +
						"</table>\n" +
						"\n" +
						"  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n" +
						"  </div>\n" +
						"</div>\n" +
						"<!--[if (mso)|(IE)]></td><![endif]-->\n" +
						"      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
						"    </div>\n" +
						"  </div>\n" +
						"</div>\n" +
						"\n" +
						"\n" +
						"\n" +
						"<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n" +
						"  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 650px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #6e6e6e;\">\n"
						+
						"    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\n"
						+
						"      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:650px;\"><tr style=\"background-color: #6e6e6e;\"><![endif]-->\n"
						+
						"      \n" +
						"<!--[if (mso)|(IE)]><td align=\"center\" width=\"650\" style=\"width: 650px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\" valign=\"top\"><![endif]-->\n"
						+
						"<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 650px;display: table-cell;vertical-align: top;\">\n"
						+
						"  <div style=\"width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\n"
						+
						"  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\"><!--<![endif]-->\n"
						+
						"  \n" +
						"<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
						+
						"  <tbody>\n" +
						"    <tr>\n" +
						"      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:50px 10px 30px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n"
						+
						"        \n" +
						"<div align=\"center\">\n" +
						"  <div style=\"display: table; max-width:187px;\">\n" +
						"  <!--[if (mso)|(IE)]><table width=\"187\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"border-collapse:collapse;\" align=\"center\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse; mso-table-lspace: 0pt;mso-table-rspace: 0pt; width:187px;\"><tr><![endif]-->\n"
						+
						"  \n" +
						"    \n" +
						"    <!--[if (mso)|(IE)]><td width=\"32\" style=\"width:32px; padding-right: 15px;\" valign=\"top\"><![endif]-->\n"
						+
						"    <table align=\"left\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"32\" height=\"32\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;margin-right: 15px\">\n"
						+
						"      <tbody><tr style=\"vertical-align: top\"><td align=\"left\" valign=\"middle\" style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\n"
						+
						"        <a href=\"https://linkedin.com/\" title=\"LinkedIn\" target=\"_blank\">\n" +
						"          <img src=\"https://cdn.tools.unlayer.com/social/icons/rounded-black/linkedin.png\" alt=\"LinkedIn\" title=\"LinkedIn\" width=\"32\" style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: block !important;border: none;height: auto;float: none;max-width: 32px !important\">\n"
						+
						"        </a>\n" +
						"      </td></tr>\n" +
						"    </tbody></table>\n" +
						"    <!--[if (mso)|(IE)]></td><![endif]-->\n" +
						"    \n" +
						"    <!--[if (mso)|(IE)]><td width=\"32\" style=\"width:32px; padding-right: 15px;\" valign=\"top\"><![endif]-->\n"
						+
						"    <table align=\"left\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"32\" height=\"32\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;margin-right: 15px\">\n"
						+
						"      <tbody><tr style=\"vertical-align: top\"><td align=\"left\" valign=\"middle\" style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\n"
						+
						"        <a href=\"https://instagram.com/\" title=\"Instagram\" target=\"_blank\">\n" +
						"          <img src=\"https://cdn.tools.unlayer.com/social/icons/rounded-black/instagram.png\" alt=\"Instagram\" title=\"Instagram\" width=\"32\" style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: block !important;border: none;height: auto;float: none;max-width: 32px !important\">\n"
						+
						"        </a>\n" +
						"      </td></tr>\n" +
						"    </tbody></table>\n" +
						"    <!--[if (mso)|(IE)]></td><![endif]-->\n" +
						"    \n" +
						"    <!--[if (mso)|(IE)]><td width=\"32\" style=\"width:32px; padding-right: 15px;\" valign=\"top\"><![endif]-->\n"
						+
						"    <table align=\"left\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"32\" height=\"32\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;margin-right: 15px\">\n"
						+
						"      <tbody><tr style=\"vertical-align: top\"><td align=\"left\" valign=\"middle\" style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\n"
						+
						"        <a href=\"https://facebook.com/\" title=\"Facebook\" target=\"_blank\">\n" +
						"          <img src=\"https://cdn.tools.unlayer.com/social/icons/rounded-black/facebook.png\" alt=\"Facebook\" title=\"Facebook\" width=\"32\" style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: block !important;border: none;height: auto;float: none;max-width: 32px !important\">\n"
						+
						"        </a>\n" +
						"      </td></tr>\n" +
						"    </tbody></table>\n" +
						"    <!--[if (mso)|(IE)]></td><![endif]-->\n" +
						"    \n" +
						"    <!--[if (mso)|(IE)]><td width=\"32\" style=\"width:32px; padding-right: 0px;\" valign=\"top\"><![endif]-->\n"
						+
						"    <table align=\"left\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"32\" height=\"32\" style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;margin-right: 0px\">\n"
						+
						"      <tbody><tr style=\"vertical-align: top\"><td align=\"left\" valign=\"middle\" style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\n"
						+
						"        <a href=\"https://youtube.com/\" title=\"YouTube\" target=\"_blank\">\n" +
						"          <img src=\"https://cdn.tools.unlayer.com/social/icons/rounded-black/youtube.png\" alt=\"YouTube\" title=\"YouTube\" width=\"32\" style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: block !important;border: none;height: auto;float: none;max-width: 32px !important\">\n"
						+
						"        </a>\n" +
						"      </td></tr>\n" +
						"    </tbody></table>\n" +
						"    <!--[if (mso)|(IE)]></td><![endif]-->\n" +
						"    \n" +
						"    \n" +
						"    <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
						"  </div>\n" +
						"</div>\n" +
						"\n" +
						"      </td>\n" +
						"    </tr>\n" +
						"  </tbody>\n" +
						"</table>\n" +
						"\n" +
						"<table style=\"font-family:arial,helvetica,sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n"
						+
						"  <tbody>\n" +
						"    <tr>\n" +
						"      <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 10px 30px;font-family:arial,helvetica,sans-serif;\" align=\"left\">\n"
						+
						"        \n" +
						"  <div style=\"color: #ffffff; line-height: 190%; text-align: center; word-wrap: break-word;\">\n"
						+
						"    <p style=\"font-size: 14px; line-height: 190%;\"><span style=\"font-family: Raleway, sans-serif; font-size: 14px; line-height: 26.599999999999998px;\">Sie erhalten diese E-Mail, da Sie einen Tisch &uuml;ber Scrumdog Millionaires reserviert haben.</span></p>\n"
						+
						"<p style=\"font-size: 14px; line-height: 190%;\"><span style=\"font-family: Raleway, sans-serif; font-size: 14px; line-height: 26.599999999999998px;\">&copy;2022 Scrumdog Millionaires | Boltzmannstra&szlig;e 3, 85748 Garching bei M&uuml;nchen</span></p>\n"
						+
						"  </div>\n" +
						"\n" +
						"      </td>\n" +
						"    </tr>\n" +
						"  </tbody>\n" +
						"</table>\n" +
						"\n" +
						"  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n" +
						"  </div>\n" +
						"</div>\n" +
						"<!--[if (mso)|(IE)]></td><![endif]-->\n" +
						"      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
						"    </div>\n" +
						"  </div>\n" +
						"</div>\n" +
						"\n" +
						"\n" +
						"    <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" +
						"    </td>\n" +
						"  </tr>\n" +
						"  </tbody>\n" +
						"  </table>\n" +
						"  <!--[if mso]></div><![endif]-->\n" +
						"  <!--[if IE]></div><![endif]-->\n" +
						"</body>\n" +
						"\n" +
						"</html>",
				LocalDate.now(), LocalTime.now());
		Email emailRes = new Email(user.getEmail(), "Reservierung bestätigt", emailResConfirmText, LocalDate.now(),
				LocalTime.now());
		Email emailResConfirm = new Email(user.getEmail(), "Bitte bestätigen Sie Ihre Reservierung", emailres,
				reservation.getDate().minusDays(1), reservation.getTime().minusHours(24));
		EmailThread.addEmail(emailRes);
		EmailThread.addEmail(emailResConfirm);

		reservation.setUser(user);
		reservations.add(reservation);
	}
}
