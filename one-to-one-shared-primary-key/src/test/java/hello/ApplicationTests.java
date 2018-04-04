package hello;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ApplicationTests {

    @Autowired
    private BookRepository bookRepository;

    @Before
    public void init() {
        // save a couple of books
        Book bookA = new Book("Spring in Action", new BookDetail(208));
        Book bookB = new Book("Spring Data in Action", new BookDetail(235));
        Book bookC = new Book("Spring Boot in Action");
        bookRepository.saveAll(Arrays.asList(bookA, bookB, bookC));
    }

    @After
    public void clear() {
        // delete all books
        bookRepository.deleteAll();
    }

    @Test
    public void save() {
        assertThat(bookRepository.findAll()).hasSize(3);
    }

    @Test
    public void findByName() {
        Book findByName = bookRepository.findByName("Spring in Action");
        assertThat(findByName).isNotNull();
        assertThat(findByName.getBookDetail()).isNotNull();
    }

    @Test
    public void deleteBookWithDetail() {
        // delete a book
        bookRepository.delete(bookRepository.findByName("Spring in Action"));
        assertThat(bookRepository.findAll()).hasSize(2);
    }

    @Test
    public void deleteBookWithoutDetail() {
        // delete a book without detail
        bookRepository.delete(bookRepository.findByName("Spring Boot in Action"));
        assertThat(bookRepository.findAll()).hasSize(2);
    }

    @Test
    public void updateBook() {
        Book findByName = bookRepository.findByName("Spring Data in Action");
        findByName.setName("Spring Data in Action - Second Edition");
        assertThat(bookRepository.findByName("Spring Data in Action")).isNull();
        assertThat(bookRepository.findByName("Spring Data in Action - Second Edition")).isNotNull();
    }

    @Test
    public void deleteBookDetail() {
        // Book (orphanRemoval = true)
        Book book = bookRepository.findByName("Spring in Action");
        book.setBookDetail(null);
        bookRepository.save(book);
        assertThat(bookRepository.findAll()).hasSize(3);
    }

    @Test
    public void addBookDetail() {
        Book book = bookRepository.findByName("Spring in Action");
        book.setBookDetail(new BookDetail(123));
        bookRepository.save(book);

        Book findByName = bookRepository.findByName("Spring in Action");
        assertThat(findByName.getBookDetail()).hasFieldOrPropertyWithValue("numberOfPages", 123);
    }

    @Test
    public void updateBookDetail() {
        // Book (orphanRemoval = true)
        Book book = bookRepository.findByName("Spring Data in Action");
        book.getBookDetail().setNumberOfPages(311);
        bookRepository.save(book);

        Book findByName = bookRepository.findByName("Spring Data in Action");
        assertThat(findByName.getBookDetail()).hasFieldOrPropertyWithValue("numberOfPages", 311);
    }

}
