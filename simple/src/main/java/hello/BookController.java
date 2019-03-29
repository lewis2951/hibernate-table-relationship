package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    private final IBookService bookService;

    @Autowired
    public BookController(IBookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/init")
    public void init() {
        bookService.init();
    }

    @PostMapping("/rename")
    public void rename(@RequestParam(name = "id") Integer id,
                       @RequestParam(name = "name") String name) {
        bookService.rename(id, name);
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

    @PostMapping("/find")
    public Book find(@RequestParam(name = "name") String name) {
        return bookService.find(name);
    }

    @PostMapping("/find/like")
    public List<Book> like(@RequestParam(name = "name") String name) {
        return bookService.like(name);
    }

    @PostMapping("/find/startsWith")
    public List<Book> startsWith(@RequestParam(name = "name") String name) {
        return bookService.startsWith(name);
    }

    @PostMapping("/find/top5")
    public List<Book> findTop5(@RequestParam(name = "name") String name) {
        return bookService.findTop5(name);
    }

}
