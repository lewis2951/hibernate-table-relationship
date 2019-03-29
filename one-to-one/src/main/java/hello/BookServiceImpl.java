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

        bookRepository.save(new Book("Spring in Action", new BookDetail(123)));
        bookRepository.save(new Book("Spring Boot in Action", new BookDetail(234)));
        bookRepository.save(new Book("Spring Microservices in Action", new BookDetail(345)));
        bookRepository.save(new Book("Spring Data in Action"));
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

}
