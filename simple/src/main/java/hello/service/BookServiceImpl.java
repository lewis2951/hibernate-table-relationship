package hello.service;

import hello.entity.Book;
import hello.repo.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements IBookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    @Override
    public void init() {
        deleteAll();

        bookRepository.save(new Book("Spring in Action"));
        bookRepository.save(new Book("Spring Boot in Action"));
        bookRepository.save(new Book("Spring Microservices in Action"));
    }

    @Override
    public void deleteAll() {
        bookRepository.deleteAll();
    }

    @Override
    public void delete(Integer id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void rename(Integer id, String newName) {
        bookRepository.findById(id).ifPresent(book -> {
            book.setName(newName);
            bookRepository.save(book);
        });
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book find(String name) {
        return bookRepository.findByName(name);
    }

    @Override
    public List<Book> startsWith(String name) {
        return bookRepository.findByNameStartsWith(name);
    }

    @Override
    public List<Book> like(String name) {
        return bookRepository.findByNameContaining(name);
    }

    @Override
    public List<Book> likeTop5(String name) {
        return bookRepository.findTop5ByNameContaining(name);
    }

}
