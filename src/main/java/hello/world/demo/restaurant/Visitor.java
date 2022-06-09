package hello.world.demo.restaurant;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

import java.time.LocalDate;

@Entity
public class Visitor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String username;
    private String email;
    private LocalDate birthday;

    @Autowired
    public Visitor(String username, String email, LocalDate birthday) {
        this.username = username;
        this.email = email;
        this.birthday = birthday;
    }

    @Autowired
    public Visitor() {

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

