package hello.world.demo.control;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hello.world.demo.control.email.EmailThread;





@SpringBootApplication
public class DemoApplication {

	private static EmailThread emailThread = new EmailThread();
	private static RestaurantOverview restaurantOverview = new RestaurantOverview();

	public static void main(String[] args) {
		emailThread.start();
		restaurantOverview.start();
		SpringApplication.run(DemoApplication.class, args);
	}
}
