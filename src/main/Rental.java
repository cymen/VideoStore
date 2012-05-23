package main;


public class Rental {

	private Movie movie;
	private int daysRented;
	
	public Rental(Movie movie, int daysRented) {
		this.movie = movie;
		this.daysRented = daysRented;
	}
	
	public Movie getMovie() {
		return movie;
	}

	public int getDaysRented() {
		return daysRented;
	}

	public int charge() {
		int result = 0;
		
		switch(this.getMovie().getPriceCode()) {
			case Movie.REGULAR:
				result += 200;
				if (this.getDaysRented() > 2) {
					result += (this.getDaysRented() - 2) * 150;
				}
				break;
			case Movie.NEW_RELEASE:
				result += this.getDaysRented() * 300;
				break;
			case Movie.CHILDRENS:
				result += 150;
				if (this.getDaysRented() > 3) {
					result += (this.getDaysRented() -3) * 150;
				}
				break;
		}
		return result;
	}

	public int frequentRenterPoints() {
		// add frequent renter points
		int subTotal = 1;
		// add bonus for two day new release rental
		if (getMovie().getPriceCode() == Movie.NEW_RELEASE && getDaysRented() > 1) {
			subTotal += 1;
		}
		return subTotal;
	}
}
