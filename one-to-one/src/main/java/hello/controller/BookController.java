package hello.controller;

import hello.entity.Book;
import hello.service.IBookService;
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

    @PostMapping("/find/page/nn")
    public List<Book> pageNotNull() {
        return bookService.pageNotNull();
    }

    @PostMapping("/find/page/ge")
    public List<Book> pageGreaterThan(@RequestParam(name = "num") Integer num) {
        return bookService.pageGreatThan(num);
    }

    @PostMapping("/find/page/bw")
    public List<Book> pageBetweenAnd(@RequestParam(name = "min") Integer min,
                                     @RequestParam(name = "max") Integer max) {
        return bookService.pageBetweenAnd(min, max);
    }

    @PostMapping("/find/like")
    public List<Book> like(@RequestParam(name = "name") String name) {
        return bookService.like(name);
    }

}
