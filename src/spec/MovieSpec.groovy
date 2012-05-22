package spec
import main.Movie;
import spock.lang.*;

class MovieSpec extends Specification {
	def "constructor sets title and price code"() {
		def movie = new Movie("Once Upon a Time in the West", Movie.NEW_RELEASE)

		expect:
		movie.getTitle() == 'Once Upon a Time in the West'
		movie.getPriceCode() == Movie.NEW_RELEASE		
	}
}
