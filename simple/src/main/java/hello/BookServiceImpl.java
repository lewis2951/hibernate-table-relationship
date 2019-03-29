package hello;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements IBookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void init() {
        deleteAll();

        bookRepository.save(new Book("Spring in Action"));
        bookRepository.save(new Book("Spring Boot in Action"));
        bookRepository.save(new Book("Spring Microservices in Action"));
    }

    @Override
    public void rename(Integer id, String name) {
        bookRepository.findById(id).ifPresent(book -> {
            book.setName(name);
            bookRepository.save(book);
        });
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
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book find(String name) {
        return bookRepository.findByName(name);
    }

    @Override
    public List<Book> like(String name) {
        return bookRepository.findByNameContaining(name);
    }

    @Override
    public List<Book> startsWith(String name) {
        return bookRepository.findByNameStartsWith(name);
    }

    @Override
    public List<Book> findTop5(String name) {
        return bookRepository.findTop5ByNameContaining(name);
    }

}
