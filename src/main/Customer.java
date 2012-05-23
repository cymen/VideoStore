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
		
		Iterator<Rental> iterator = this.rentals.iterator();
		while (iterator.hasNext()) {		
			Rental rental = iterator.next();
			int thisAmount = amountFor(rental);
			
			// add frequent renter points
			frequentRenterPoints += 1;
			// add bonus for two day new release rental
			if (rental.getMovie().getPriceCode() == Movie.NEW_RELEASE && rental.getDaysRented() > 1) {
				frequentRenterPoints += 1;
			}
			
			// show figures for this rental
			result += String.format("\t%s\t%s\n", rental.getMovie().getTitle(), "a");
			totalAmount += thisAmount;
		}
		
		// add footer lines
		result += String.format("Amount owed is %s\n", ConvertCentsToDollars(totalAmount));
		result += String.format("You earned %s frequent renter points\n", frequentRenterPoints);
		
		return result;
	}

	private int amountFor(Rental rental) {
		int thisAmount = 0;
		
		// determine amounts for each line
		switch(rental.getMovie().getPriceCode()) {
			case Movie.REGULAR:
				thisAmount += 200;
				if (rental.getDaysRented() > 2) {
					thisAmount += (rental.getDaysRented() - 2) * 150;
				}
				break;
			case Movie.NEW_RELEASE:
				thisAmount += rental.getDaysRented() * 300;
				break;
			case Movie.CHILDRENS:
				thisAmount += 150;
				if (rental.getDaysRented() > 3) {
					thisAmount += (rental.getDaysRented() -3) * 150;
				}
				break;
		}
		return thisAmount;
	}
	
	public BigDecimal ConvertCentsToDollars(int cents) {		
		return new BigDecimal((float) cents/100).setScale(2, BigDecimal.ROUND_HALF_UP);
	}
}
