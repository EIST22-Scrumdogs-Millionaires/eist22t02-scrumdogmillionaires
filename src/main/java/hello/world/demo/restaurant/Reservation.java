package hello.world.demo.restaurant;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation {
    private static int ID=0;
    private int id;
    private LocalTime time;
    private LocalDate date;
    private Table table;

    public Reservation(int id, LocalTime time, LocalDate date) {
        this.id = ID++;
        this.time = time;
        this.date = date;
    }
}
