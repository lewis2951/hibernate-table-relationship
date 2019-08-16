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

    @PostMapping("/rename")
    public void rename(@RequestParam(name = "id") Integer id,
                       @RequestParam(name = "name") String newName) {
        bookService.rename(id, newName);
    }

    @PostMapping("/findAll")
    public List<Book> findAll() {
        return bookService.findAll();
    }

    @PostMapping("/find")
    public Book find(@RequestParam(name = "name") String name) {
        return bookService.find(name);
    }

    @PostMapping("/startsWith")
    public List<Book> startsWith(@RequestParam(name = "name") String name) {
        return bookService.startsWith(name);
    }

    @PostMapping("/like")
    public List<Book> like(@RequestParam(name = "name") String name) {
        return bookService.like(name);
    }

    @PostMapping("/likeTop5")
    public List<Book> likeTop5(@RequestParam(name = "name") String name) {
        return bookService.likeTop5(name);
    }

}
