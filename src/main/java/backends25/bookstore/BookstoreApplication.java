package backends25.bookstore;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import backends25.bookstore.domain.AppUser;
import backends25.bookstore.domain.AppUserRepository;
import backends25.bookstore.domain.Book;
import backends25.bookstore.domain.BookRepository;
import backends25.bookstore.domain.Category;
import backends25.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demoData(BookRepository BookRepository, 
		CategoryRepository CategoryRepository, AppUserRepository appUserRepository) {
		return (args) -> {


			// Save a couple of categories
			Category WarCategory = CategoryRepository.save(new Category("War"));
			Category FictionCategory = CategoryRepository.save(new Category("Fiction"));
			Category ScienceCategory = CategoryRepository.save(new Category("Science"));

			
			// Save a couple of books
			log.info("save some books");
			BookRepository.save(new Book("A Farewell to Arms", "Ernest HemingWay", "1232323-21", 20, 1929, WarCategory));
			BookRepository.save(new Book("Animal Farm", "George Orwell", "2212343-5", 15, 1945, ScienceCategory));
			BookRepository.save(new Book("Pimeän pyöveli", "Ilkka Remes", "978-951-0-30683-3", 17, 2005, FictionCategory));
		
			log.info("create application users");
			appUserRepository
					.save(new AppUser("user", "$2a$10$o2D0V/eq.l0tvt93qiAEQOAG69bryuxWSL0m/IAmkuVOZfLc.gZ5m", "USER"));
					appUserRepository.save(new AppUser("admin", "$2a$10$UHGHg1rFglNRVgpuFUxQ3O.9LKoxqsQl3Qs6zZSE0FfqP8luZs9uC", "ADMIN"));

		};
	}

}
