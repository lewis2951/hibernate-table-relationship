package hello;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    private final IBookService bookService;

    public BookController(IBookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/init")
    public void init() {
        bookService.init();
    }

    @PostMapping("/deleteAll")
    public void deleteAll() {
        bookService.deleteAll();
    }

    @PostMapping("/delete")
    public void delete(@RequestParam(name = "id") Integer id) {
        bookService.delete(id);
    }

    @PostMapping("/findAll")
    public List<Book> findAll() {
        return bookService.findAll();
    }

}
