package hello.world.demo;

import hello.world.demo.restaurant.Location;
import hello.world.demo.restaurant.Restaurant;

import java.sql.Time;
import java.util.ArrayList;

public class Data {

    private Restaurant[] sampleRestaurants;

    public Data() {
        this.sampleRestaurants = new Restaurant[]{
                new Restaurant("DA VINCI","Italienisches Restaurant und Pizzeria", null,
                        null, null, null, null, null, "https://davincirestaurants.de", "$$"
                        )

        };
    }

    public synchronized static Restaurant[] getAllRestaurants (){
        return new Restaurant[]{
                new Restaurant("DA VINCI","Italienisches Restaurant und Pizzeria", null,
                        null, null, null, null, null, "https://davincirestaurants.de", "$$"
                )
        };
    }
}
