package hello;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ApplicationTests {

    @Autowired
    private BookRepository bookRepository;

    @Before
    public void init() {
        Book bookA = new Book("Spring in Action");
        Book bookB = new Book("Spring Boot in Action");
        Book bookC = new Book("Spring Data in Action");
        bookRepository.saveAll(Arrays.asList(bookA, bookB, bookC));
    }

    @After
    public void deleteAll() {
        bookRepository.deleteAll();
    }

    @Test
    public void findAll() {
        assertThat(bookRepository.findAll()).hasSize(3);
    }

    @Test
    public void findByName() {
        assertThat(bookRepository.findByName("Spring in Action")).isNotNull();
        assertThat(bookRepository.findByName("Spring in")).isNull();
    }

    @Test
    public void findByNameContaining() {
        assertThat(bookRepository.findByNameContaining("Spring")).hasSize(3);
    }

    @Test
    public void marge() {
        Book book = bookRepository.findByName("Spring in Action");
        assertThat(book).isNotNull();

        book.setName("Spring in Action (5th Edition)");
        bookRepository.save(book);

        assertThat(bookRepository.findByName("Spring in Action")).isNull();
        assertThat(bookRepository.findByName("Spring in Action (5th Edition)")).isNotNull();
    }

    @Test
    public void delete() {
        Book book = bookRepository.findByName("Spring in Action");
        assertThat(book).isNotNull();

        bookRepository.delete(book);

        assertThat(bookRepository.findAll()).hasSize(2);
        assertThat(bookRepository.findByName("Spring in Action")).isNull();
    }

}
