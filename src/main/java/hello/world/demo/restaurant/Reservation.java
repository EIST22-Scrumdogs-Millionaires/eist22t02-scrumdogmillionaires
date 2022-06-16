package hello.world.demo.restaurant;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Reservation {

    private int id;

    private LocalTime time;

    private LocalDate date;

    private Visitor user;

    private Tisch table;

    public Reservation(LocalTime time, LocalDate date, Tisch table, Visitor user) {
        this.time = time;
        this.date = date;
        this.user = user;
        this.table = table;

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
