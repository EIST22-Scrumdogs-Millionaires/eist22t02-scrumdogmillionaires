package hello.world.demo.restaurant;

public class Tisch {

	private int id;

	private int seats;

	private boolean available;


	public Tisch(int id, int seats) {
		this.id = id;
		this.seats = seats;
		this.available = true;
		
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

	public boolean getAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
}
