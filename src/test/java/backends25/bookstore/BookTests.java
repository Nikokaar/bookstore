package backends25.bookstore;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import backends25.bookstore.domain.Book;
import backends25.bookstore.domain.BookRepository;
import backends25.bookstore.domain.Category;
import backends25.bookstore.domain.CategoryRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BookTests {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void findCategoriesShouldReturnAtLeastOneCategory() {
        assertThat(categoryRepository.count()).isGreaterThan(0);
    }

    @Test
    public void saveBook() {
        Category category = categoryRepository.findByName("War").orElseThrow();
        Book book = new Book("Tuntematon sotilas", "Väinö Linna", "951-0-29646-5", 20, 1954, category);
        bookRepository.save(book);

        Optional<Book> savedBook = bookRepository.findByTitle("Tuntematon sotilas");
        assertThat(savedBook).isPresent();
        assertThat(savedBook.get().getAuthor()).isEqualTo("Väinö Linna");
    }

    @Test
    public void getCorrectData() {
        Optional<Book> book = bookRepository.findByTitle("A Farewell to Arms");
        assertThat(book).isPresent();
        assertThat(book.get().getTitle()).isEqualTo("A Farewell to Arms");
        assertThat(book.get().getAuthor()).isEqualTo("Ernest HemingWay");
    }

    @Test
    public void deleteBook() {
        List<Book> books = bookRepository.findByCategoryName("War");
        assertThat(books).isNotEmpty();

        Book bookToDelete = books.get(0);
        bookRepository.delete(bookToDelete);

        List<Book> newBooks = bookRepository.findByCategoryName("War");
        assertThat(newBooks).hasSize(books.size() - 1);
    }
}
