package backends25.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import backends25.bookstore.BookRepository;
import backends25.bookstore.domain.Book;

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

       @RequestMapping(value = "/add")
    public String addStudent(Model model){
    	model.addAttribute("book", new Book());
        return "addbook";
    }  

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book){
        repository.save(book);
        return "redirect:booklist";
    }    
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
    	repository.deleteById(bookId);
        return "redirect:/booklist";
    }  
    
}