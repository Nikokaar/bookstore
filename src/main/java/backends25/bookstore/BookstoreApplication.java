package backends25.bookstore;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import backends25.bookstore.domain.Book;
import backends25.bookstore.domain.Category;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demoData(BookRepository BookRepository, CategoryRepository CategoryRepository) {
		return (args) -> {


			// Save a couple of categories
			Category WarCategory = CategoryRepository.save(new Category("War"));
			Category FictionCategory = CategoryRepository.save(new Category("Fiction"));
			Category ScienceCategory = CategoryRepository.save(new Category("Science"));

			
			// Save a couple of books
			log.info("save some books");
			BookRepository.save(new Book("A Farewell to Arms", "Ernest HemingWay", "1232323-21", 20, 1929, WarCategory));
			BookRepository.save(new Book("Animal Farm", "George Orwell", "2212343-5", 15, 1945, ScienceCategory));
			BookRepository.save(new Book("Pimeän pyöveli", "Ilkka Remes", "978-951-0-30683-3", 17, 2005, ScienceCategory));
		};
	}

}
