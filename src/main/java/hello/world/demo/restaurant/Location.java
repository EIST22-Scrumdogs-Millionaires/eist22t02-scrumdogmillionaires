package hello.world.demo.restaurant;

public class Location {

    private int Xcoordinate;

    private int Ycoordinate;

    private String city;

    private String street;

    private String streetnumber;

    private String plz;

    public Location(int xcoordinate, int ycoordinate, String city, String street, String streetnumber, String plz) {
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
