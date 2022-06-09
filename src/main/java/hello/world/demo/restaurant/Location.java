package hello.world.demo.restaurant;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;
    @Column(name="Xcoordinate")
    private int Xcoordinate;
    @Column(name="Ycoordinate")
    private int Ycoordinate;
    @Column(name="city")
    private String city;
    @Column(name="street")
    private String street;
    @Column(name="streetnumber")
    private String streetnumber;
    @Column(name="plz")
    private String plz;

    @Autowired
    public Location(int xcoordinate, int ycoordinate, String city, String street, String streetnumber, String plz) {
        Xcoordinate = xcoordinate;
        Ycoordinate = ycoordinate;
        this.city = city;
        this.street = street;
        this.streetnumber = streetnumber;
        this.plz = plz;
    }

    @Autowired
    public Location() {

    }

    public int getXcoordinate() {
        return Xcoordinate;
    }

    public void setXcoordinate(int xcoordinate) {
        Xcoordinate = xcoordinate;
    }

    public int getYcoordinate() {
        return Ycoordinate;
    }

    public void setYcoordinate(int ycoordinate) {
        Ycoordinate = ycoordinate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetnumber() {
        return streetnumber;
    }

    public void setStreetnumber(String streetnumber) {
        this.streetnumber = streetnumber;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }
}
