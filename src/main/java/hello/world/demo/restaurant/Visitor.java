package hello.world.demo.restaurant;

import java.time.LocalDate;

public class Visitor {

    private int id;

    private String username;

    private String email;

    private LocalDate birthday;

    public Visitor(String username, String email, LocalDate birthday) {
        this.username = username;
        this.email = email;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

}
