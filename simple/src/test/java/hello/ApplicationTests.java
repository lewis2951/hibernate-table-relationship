package hello;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ApplicationTests {

    @Autowired
    private BookRepository bookRepository;

    @Before
    public void init() {
        bookRepository.save(new Book("Spring in Action"));
        bookRepository.save(new Book("Spring Boot in Action"));
        bookRepository.save(new Book("Spring Microservices in Action"));
    }

    @After
    public void deleteAll() {
        bookRepository.deleteAll();
    }

    @Test
    public void rename() {

    }

    @Test
    public void delete() {

    }

    @Test
    public void findAll() {
        assertThat(bookRepository.findAll()).hasSize(3);
    }

    @Test
    public void find() {

    }

    @Test
    public void like(String name) {

    }

    @Test
    public void startsWith(String name) {

    }

    @Test
    public void findTop5(String name) {

    }

}