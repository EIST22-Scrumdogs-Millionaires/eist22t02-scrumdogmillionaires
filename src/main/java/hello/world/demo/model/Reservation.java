package hello.world.demo.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation {

    private Integer id;

    private LocalTime time;

    private LocalDate date;

    private Visitor user;

    private Tisch table;

    private Integer restaurant_id;

    private String cancelsecretkey;

    private String confirmsecretkey;

    private boolean confirmed;

    public Reservation() {
    }

    public Reservation(LocalTime time, LocalDate date, Tisch table, Visitor user, Integer id, Integer restaurant_id) {
        this.id = id;
        this.time = time;
        this.date = date;
        this.user = user;
        this.table = table;
        this.restaurant_id = restaurant_id;
        this.confirmed = false;
    }

    public void generateSecretKeys() {
        this.confirmsecretkey = String.valueOf(Math.random()).replace('.', 'x');
        this.cancelsecretkey = String.valueOf(Math.random()).replace('.', 'x');
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

    public void setId(int id) {
        this.id = id;
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

    public String getCancelsecretkey() {
        return cancelsecretkey;
    }

    public String getConfirmsecretkey() {
        return confirmsecretkey;
    }

    public void setCancelsecretkey(String cancelsecretkey) {
        this.cancelsecretkey = cancelsecretkey;
    }

    public void setConfirmsecretkey(String confirmsecretkey) {
        this.confirmsecretkey = confirmsecretkey;
    }

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public boolean getConfirmed() {
        return confirmed;
    }

    public void setRestaurant_id(Integer restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public void confirmReservation(String actionSecretKey) {
        if (actionSecretKey.compareTo(confirmsecretkey) == 0) {
            confirmed = true;
        }
    }

}
