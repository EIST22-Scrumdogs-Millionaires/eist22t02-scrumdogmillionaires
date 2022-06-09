package hello.world.demo.restaurant;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private LocalTime time;
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "table_id")
    private Table table;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Visitor user;
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Autowired
    public Reservation(LocalTime time, LocalDate date, Visitor user, Restaurant restaurant) {
        this.time = time;
        this.date = date;
        this.user = user;
        this.restaurant = restaurant;
    }

    @Autowired
    public Reservation() {

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

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
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
