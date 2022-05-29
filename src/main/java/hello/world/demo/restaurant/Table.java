package hello.world.demo.restaurant;

import java.util.List;

public class Table {
    private int id;
    private int seats;
    private Restaurant restaurant;

    public Table(int id, int seats, Restaurant restaurant) {
        this.id = id;
        this.seats = seats;
        this.restaurant = restaurant;
    }

}
