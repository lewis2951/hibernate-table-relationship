package hello.service;

import hello.entity.Book;
import hello.entity.BookDetail;
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

        bookRepository.save(new Book("Spring in Action", new BookDetail(123)));
        bookRepository.save(new Book("Spring Boot in Action", new BookDetail(234)));
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
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> pageNotNull() {
        return bookRepository.findBooksByBookDetail_NumberOfPagesNotNull();
    }

    @Override
    public List<Book> pageGreatThan(int num) {
//        return bookRepository.findBooksByNumberOfPagesGreaterThan(num);
        return null;
    }

    @Override
    public List<Book> pageBetweenAnd(int min, int max) {
//        return bookRepository.findBooksByNumberOfPagesBetweenAnd(min, max);
        return null;
    }

    @Override
    public List<Book> like(String name) {
        return bookRepository.findByNameContaining(name);
    }

}
