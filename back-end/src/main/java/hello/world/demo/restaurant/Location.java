package hello.world.demo.restaurant;

public class Location {
    private int Xcoordinate;
    private int Ycoordinate;
    private String city;
    private String street;
    private int streetnumber;
    private int plz;

    public Location(int xcoordinate, int ycoordinate, String city, String street, int streetnumber, int plz) {
        Xcoordinate = xcoordinate;
        Ycoordinate = ycoordinate;
        this.city = city;
        this.street = street;
        this.streetnumber = streetnumber;
        this.plz = plz;
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

    public int getStreetnumber() {
        return streetnumber;
    }

    public void setStreetnumber(int streetnumber) {
        this.streetnumber = streetnumber;
    }

    public int getPlz() {
        return plz;
    }

    public void setPlz(int plz) {
        this.plz = plz;
    }
}
