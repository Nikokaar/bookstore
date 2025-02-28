package backends25.bookstore;

import org.springframework.data.repository.CrudRepository;

import backends25.bookstore.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long>{

}
