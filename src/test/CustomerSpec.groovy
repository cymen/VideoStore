package test
import main.Customer;
import main.Movie;
import main.Rental;
import spock.lang.*;

class CustomerSpec extends Specification {
	def "can convert cents to dollars"() {
		def customer = new Customer();
		
		expect:
		customer.ConvertCentsToDollars(0) == 0.00
		customer.ConvertCentsToDollars(150) == 1.50
		customer.ConvertCentsToDollars(300001) == 3000.01
	}
	
	def "sets name via constructor"() {
		def customer = new Customer("Sergio Leone");
		
		expect:
		customer.getName() == "Sergio Leone"		
	}
	
	def "can keep track of rentals"() {
		def customer = new Customer("a")
		
		expect:
		customer.getRentals().isEmpty() == true
		
		when:
		def movie = new Movie("abc", Movie.REGULAR)
		def rental = new Rental(movie, 4)
		customer.addRental(rental)
		
		then:
		customer.getRentals().size() == 1		
	}
		
	def "a customer with no rentals produces a statement with no total"() {
		def customer = new Customer("Sergio Leone")
		
		expect:
		customer.Statement().contains("Amount owed is 0.00\n") == true
	}
	
	def "a customer with one new movie for children for one day will owe a total of 1.50"() {
		def movie = new Movie("abc", Movie.CHILDRENS)
		def rental = new Rental(movie, 1)
		def customer = new Customer("Sergio Leone")
		
		when:
		customer.addRental(rental)
		
		then:
		customer.Statement().contains("Amount owed is 1.50\n") == true
	}
}
