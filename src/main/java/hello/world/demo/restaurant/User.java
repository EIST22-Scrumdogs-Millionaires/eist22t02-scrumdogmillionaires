package hello.world.demo.restaurant;

import java.time.LocalDate;
import java.util.List;

public class User {
    private String username;
    private String email;
    private LocalDate birthday;

    public User(String username, String email, LocalDate birthday) {
        this.username = username;
        this.email = email;
        this.birthday = birthday;
    }
}
