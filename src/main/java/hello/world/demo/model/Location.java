package hello.world.demo.model;

public class Location {

    private double xcoordinate;

    private double ycoordinate;

    private String city;

    private String street;

    private String streetnumber;

    private String plz;

    public Location() {
    }

    public Location(double xcoordinate, double ycoordinate, String city, String street, String streetnumber,
            String plz) {
        this.xcoordinate = xcoordinate;
        this.ycoordinate = ycoordinate;
        this.city = city;
        this.street = street;
        this.streetnumber = streetnumber;
        this.plz = plz;
    }

    public double getXcoordinate() {
        return xcoordinate;
    }

    public void setXcoordinate(double xcoordinate) {
        this.xcoordinate = xcoordinate;
    }

    public double getYcoordinate() {
        return ycoordinate;
    }

    public void setYcoordinate(double ycoordinate) {
        this.ycoordinate = ycoordinate;
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
