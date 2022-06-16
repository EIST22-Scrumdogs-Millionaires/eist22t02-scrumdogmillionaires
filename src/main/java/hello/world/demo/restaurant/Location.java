package hello.world.demo.restaurant;

public class Location {

    private double Xcoordinate;

    private double Ycoordinate;

    private String city;

    private String street;

    private String streetnumber;

    private String plz;

    public Location(double xcoordinate, double ycoordinate, String city, String street, String streetnumber,
            String plz) {
        this.Xcoordinate = xcoordinate;
        this.Ycoordinate = ycoordinate;
        this.city = city;
        this.street = street;
        this.streetnumber = streetnumber;
        this.plz = plz;
    }

    public double getXcoordinate() {
        return Xcoordinate;
    }

    public void setXcoordinate(double xcoordinate) {
        this.Xcoordinate = xcoordinate;
    }

    public double getYcoordinate() {
        return Ycoordinate;
    }

    public void setYcoordinate(double ycoordinate) {
        this.Ycoordinate = ycoordinate;
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
