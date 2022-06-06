package hello.world.demo;

import hello.world.demo.restaurant.Location;
import hello.world.demo.restaurant.Restaurant;

import java.util.ArrayList;

public class Data {

    private Restaurant[] sampleRestaurants;

    public Data() {
        this.sampleRestaurants = new Restaurant[]{
            /*
                new Restaurant("DA VINCI","Italienisches Restaurant und Pizzeria",
                        new Location(42,420,"München","Bayerstraße",51,187),
                        null
                        )
                        */
        };
    }

    public synchronized static Restaurant[] getAllRestaurants (){
        return new Restaurant[]{
               /*
                new Restaurant("DA VINCI","Italienisches Restaurant und Pizzeria",
                        new Location(42,420,"München","Bayerstraße",51,187),
                        null
                )
               */
        };
    }
}
