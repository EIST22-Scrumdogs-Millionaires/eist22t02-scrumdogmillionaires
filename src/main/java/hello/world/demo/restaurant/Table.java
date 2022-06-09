package hello.world.demo.restaurant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Entity;

@Entity
public class Table {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int seats;
	private Restaurant restaurant;

	public Table(int id, int seats, Restaurant restaurant) {
		this.id = id;
		this.seats = seats;
		this.restaurant = restaurant;
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
