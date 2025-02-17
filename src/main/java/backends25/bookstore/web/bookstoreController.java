package backends25.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import backends25.bookstore.BookRepository;

@Controller
public class bookstoreController {
    
    private BookRepository repository; 

    public bookstoreController(BookRepository repository) {
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
}