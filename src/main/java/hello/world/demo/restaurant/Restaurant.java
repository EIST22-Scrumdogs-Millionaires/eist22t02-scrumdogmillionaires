package hello.world.demo.restaurant;

import java.util.List;

public class Restaurant {
    private String name;
    private String description;
    private Location location;
    private List pictures;

    public Restaurant(String name, String description, Location location, List pictures) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.pictures = pictures;
    }
}
