package hello.world.demo.restaurant;

public class Tisch {

	private int id;

	private int seats;


	public Tisch(int id, int seats) {
		this.id = id;
		this.seats = seats;
		
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
}
