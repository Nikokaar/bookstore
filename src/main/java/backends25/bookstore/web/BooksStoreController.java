package backends25.bookstore.web;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



import backends25.bookstore.BookRepository;
import backends25.bookstore.CategoryRepository;
import backends25.bookstore.domain.Book;
import backends25.bookstore.domain.Category;
import jakarta.validation.Valid;


@Controller
public class BooksStoreController {
    @Autowired
    private BookRepository repository; 
    private static final Logger log = LoggerFactory.getLogger(BooksStoreController.class);

    @Autowired
	private CategoryRepository crepository; 
    // private final BookRepository bookRepository;
    public BooksStoreController(BookRepository repository) {
        this.repository = repository;
    }
    
    

    
    @GetMapping("/index")
    public String index() {
        return "index";
    }
   
    @GetMapping("/booklist")
    public String showAllBooks(Model model) {
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }

    @GetMapping("/add")
    public String addBook(Model model){
    	model.addAttribute("book", new Book());
        model.addAttribute("categories", crepository.findAll());
        return "addbook";
    }  
/* 
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book){
        repository.save(book);
        return "redirect:booklist";
    }    
*/    

    @PostMapping("/save")
    public String saveBook(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult, Model model) {
        log.info("CONTROLLER: Save the book - check validation of book: " + book);
        if (bindingResult.hasErrors()) {
            log.info("some validation error happened, book: " + book);
            model.addAttribute("editBook", book);
            return "addBook";

        }
        repository.save(book);
        return "redirect:booklist";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId) {
    	repository.deleteById(bookId);
        return "redirect:/booklist";
    }  

    @GetMapping("/editBook/{id}")
    public String editBook(@PathVariable("id") Long id, Model model) {
        model.addAttribute("editBook", repository.findById(id));
        model.addAttribute("categories", crepository.findAll());
        return "editbook";
    }

      @PostMapping("/saveEditedBook")
    public String saveEditedBook(@Valid @ModelAttribute("editBook") Book book, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("editBook", book);
  

            return "editbook";
        }
        repository.save(book);
        return "redirect:booklist";
    }

    
}