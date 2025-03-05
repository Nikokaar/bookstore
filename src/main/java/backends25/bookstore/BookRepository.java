package backends25.bookstore;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import backends25.bookstore.domain.Book;

public interface BookRepository extends CrudRepository<Book, Long>  {
    List<Book> findByCategoryName(String name);

}
