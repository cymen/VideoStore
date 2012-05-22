package test
import main.Movie;
import main.Rental;
import spock.lang.*;

class RentalSpec extends Specification {
	def "constructor sets movie and days rented"() {
		def movie = new Movie("Once Upon a Time in the West", Movie.NEW_RELEASE)
		def rental = new Rental(movie, 4)

		expect:
		rental.getMovie() == movie
		rental.getDaysRented() == 4		
	}
}
