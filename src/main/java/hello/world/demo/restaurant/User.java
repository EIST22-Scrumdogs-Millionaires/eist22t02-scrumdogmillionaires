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
