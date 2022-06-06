package hello.world.demo.restaurant;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Reservation {
    private static int ID = 0;
    private int id;
    private LocalTime time;
    private LocalDate date;
    private Table table;

    public Reservation(int id, LocalTime time, LocalDate date) {
        this.id = ID++;
        this.time = time;
        this.date = date;
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
