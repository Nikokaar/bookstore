package backends25.bookstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import backends25.bookstore.domain.BookRepository;

@SpringBootTest
class BookstoreApplicationTests {
	@Autowired
	private BookRepository bookRepository;
	@Test
	void contextLoads() {
	}

	// Testataan, että tietokantaan saadaan yhteys
	@Test
	public void testDatabaseConnection() {
		assertThat(bookRepository).isNotNull(); // Varmistetaan, että repository on injektoitu
		assertThat(bookRepository.count()).isNotNull(); // Tehdään kysely ja varmistetaan, ettei se aiheuta virhettä
	}
	
}
