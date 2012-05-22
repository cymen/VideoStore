package test
import main.Customer;
import main.Movie;
import main.Rental;
import spock.lang.*;

class CustomerSpec extends Specification {
	def "can convert cents to dollars"() {
		setup:
		def customer = new Customer();
		
		expect:
		customer.ConvertCentsToDollars(0) == 0.00
		customer.ConvertCentsToDollars(150) == 1.50		
	}
	
	def "constructor sets name"() {
		setup:
		def customer = new Customer("Sergio Leone");
		
		expect:
		customer.getName() == "Sergio Leone"		
	}
	
	def "a new customer has no rentals"() {
		setup:
		def customer = new Customer("a")

		expect:
		customer.getRentals().isEmpty() == true		
	}
	
	def "adding one rental increases the rental count by one"() {
		setup:
		def movie = new Movie("abc", Movie.REGULAR)
		def rental = new Rental(movie, 4)
		def customer = new Customer("a")
		customer.addRental(rental)
		
		expect:
		customer.getRentals().size() == 1
	}
	
	def "a customer with no rentals produces a statement with no total"() {
		setup:
		def customer = new Customer("Sergio Leone")
		def statement = customer.getStatement()
		
		expect:
		statement.contains("Rental Record for Sergio Leone\n") == true
		statement.contains("Amount owed is 0.00\n") == true
	}
	
	def "a customer with one new movie for children for one day will owe a total of 1.50"() {
		setup:
		def movie = new Movie("abc", Movie.CHILDRENS)
		def rental = new Rental(movie, 1)
		def customer = new Customer("Sergio Leone")
		customer.addRental(rental)
		def statement = customer.getStatement()
		
		expect:
		statement.contains("Rental Record for Sergio Leone\n") == true
		statement.contains("Amount owed is 1.50\n") == true
	}
}
