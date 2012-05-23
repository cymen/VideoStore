package main;

import java.math.BigDecimal;
import java.util.*;

public class Customer {

	private String name;
	private List<Rental> rentals;
	
	public Customer(String name) {
		this.name = name;
		this.rentals = new ArrayList<Rental>();
	}
	
	public void addRental(Rental rental)
	{
		this.rentals.add(rental);
	}

	public String getName() {
		return name;
	}
	
	public List<Rental> getRentals() {
		return rentals;
	}
	
	public String Statement() {
		int totalAmount = 0;
		int frequentRenterPoints = 0;
		String result = String.format("Rental Record for %s\n", this.name);
		
		for ( Rental rental : this.rentals) {		
						
			frequentRenterPoints += rental.frequentRenterPoints();
			
			// show figures for this rental
			result += String.format("\t%s\t%s\n", rental.getMovie().getTitle(), rental.charge());
			totalAmount += rental.charge();
		}
		
		// add footer lines
		result += String.format("Amount owed is %s\n", ConvertCentsToDollars(totalAmount));
		result += String.format("You earned %s frequent renter points\n", frequentRenterPoints);
		
		return result;
	}

	public BigDecimal ConvertCentsToDollars(int cents) {		
		return new BigDecimal((float) cents/100).setScale(2, BigDecimal.ROUND_HALF_UP);
	}
}
