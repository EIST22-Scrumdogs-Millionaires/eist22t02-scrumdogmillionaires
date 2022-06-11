package hello.world.demo.restaurant;

import java.time.LocalTime;
import java.util.List;

import javax.persistence.*;
import javax.persistence.Table;


import hello.world.demo.EmailService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;


public class SmallRestaurant {


	private int id;


    private String name;

    private String description;
	
	private Location location;

    private String website;

    private String priceCategory;


    public SmallRestaurant(String name, String description, Location location, 
			  String website, String priceCategory) {
		
		this.name = name;
		this.description = description;
		this.location = location;


		this.website = website;
		this.priceCategory = priceCategory;

	}


	public int getId() {
		return id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}


	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getPriceCategory() {
		return priceCategory;
	}

	public void setPriceCategory(String priceCategory) {
		this.priceCategory = priceCategory;
	}
}