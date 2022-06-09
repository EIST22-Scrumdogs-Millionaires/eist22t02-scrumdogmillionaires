package hello.world.demo.restaurant;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.persistence.Table;

import javax.persistence.Entity;

@Entity(name = "Tisch")
@Table(name = "tisch")
public class Tisch {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="table_id")
	private int id;
	@Column(name="seats")
	private int seats;
	@ManyToOne
	@JoinColumn(referencedColumnName = "restaurant_id")
	private Restaurant restaurant;

	@Autowired
	public Tisch(int id, int seats, Restaurant restaurant) {
		this.id = id;
		this.seats = seats;
		this.restaurant = restaurant;
	}

	@Autowired
	public Tisch() {

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
