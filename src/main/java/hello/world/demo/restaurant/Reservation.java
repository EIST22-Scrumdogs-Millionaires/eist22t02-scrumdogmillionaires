package hello.world.demo.restaurant;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity(name = "Reservation")
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reservation_id")
    private int id;
    @Column(name = "time")
    private LocalTime time;
    @Column(name = "date")
    private LocalDate date;
    @ManyToOne
    @JoinColumn(referencedColumnName = "table_id")
    private Tisch table;
    @ManyToOne
    @JoinColumn(referencedColumnName = "visitor_id")
    private Visitor user;
    @ManyToOne
    @JoinColumn(referencedColumnName = "restaurant_id")
    private Restaurant restaurant;

    @Autowired
    public Reservation(LocalTime time, LocalDate date,Tisch table, Visitor user, Restaurant restaurant) {
        this.time = time;
        this.date = date;
        this.user = user;
        this.restaurant = restaurant;
        this.table=table;
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

    public Tisch getTable() {
        return table;
    }

    public void setTable(Tisch table) {
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
