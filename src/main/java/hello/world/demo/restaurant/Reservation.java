package hello.world.demo.restaurant;




import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Reservation {

    private int id;
   
    private LocalTime time;
  
    private LocalDate date;
   
    private Visitor user;

    private Restaurant restaurant;

  
    public Reservation(LocalTime time, LocalDate date,Tisch table, Visitor user, Restaurant restaurant) {
        this.time = time;
        this.date = date;
        this.user = user;
        this.restaurant = restaurant;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Visitor getUser() {
        return user;
    }

    public void setUser(Visitor user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    // oder boolean?
    public void confirmReservation() {

    }

    // oder boolean?
    public void cancelReservation() {

    }

    // oder boolean?
    public void saveCalendarEvent() {

    }

    public void createReservation(Restaurant restaurant, Date date, String email) {

    }

    public void verifyEmail() {

    }

    public void confirmCancellation() {

    }
}
