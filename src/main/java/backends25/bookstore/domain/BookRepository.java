package backends25.bookstore.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long>  {
    Optional<Book> findByTitle(String title);

    List<Book> findByCategoryName(String categoryName);
}
