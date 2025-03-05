package backends25.bookstore.web;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import backends25.bookstore.BookRepository;
import backends25.bookstore.CategoryRepository;
import backends25.bookstore.domain.Book;
import backends25.bookstore.BookRepository;
import backends25.bookstore.CategoryRepository; 

@RestController
public class BookStoreRestController {

    private static final Logger log = LoggerFactory.getLogger(BookStoreRestController.class);

    private final BookRepository bookRepository;
    private CategoryRepository categoryRepository;

    public BookStoreRestController(BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;


    }

    // return list of books
    @GetMapping("/books")
    public Iterable<Book> getBooks() {
        log.info("//fetch and return books");
        return bookRepository.findAll();
    }

    // add new book
    @PostMapping("books")
    Book newBook(@RequestBody Book newBook) {
        log.info("save new book " + newBook);
        return bookRepository.save(newBook);
    }
    
    // find one book and return it
    @GetMapping("/books/{id}")
    Optional<Book> getBook(@PathVariable Long id) {
        log.info("find book, id = " + id);
        return bookRepository.findById(id);
    }
    
}
