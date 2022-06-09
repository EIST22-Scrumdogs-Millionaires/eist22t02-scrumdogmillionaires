package hello.world.demo.restaurant;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

import javax.persistence.Entity;

@Entity
public class Table {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int seats;
	@ManyToOne
	@JoinColumn(name = "restaurant_id")
	private Restaurant restaurant;

	@Autowired
	public Table(int id, int seats, Restaurant restaurant) {
		this.id = id;
		this.seats = seats;
		this.restaurant = restaurant;
	}

	@Autowired
	public Table() {

	}

	public int getId() {
		return id;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
}
