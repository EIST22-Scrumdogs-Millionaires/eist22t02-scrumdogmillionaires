package hello.world.demo.restaurant;

import java.time.LocalTime;
import java.util.List;

public class Restaurant {
    private String name;
    private String description;
    private Location location;
    private List<String> pictures;
    private List<String> ratings;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private String website;
    private String priceCategory;

    public Restaurant(String name, String description, Location location, List<String> pictures) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.pictures = pictures;
    }

}
