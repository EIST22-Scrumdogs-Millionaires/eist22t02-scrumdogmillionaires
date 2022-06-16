package hello.world.demo.restaurant;

public class Tisch {

	private int id;

	private int seats;

	private Restaurant restaurant;

	public Tisch(int id, int seats, Restaurant restaurant) {
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
