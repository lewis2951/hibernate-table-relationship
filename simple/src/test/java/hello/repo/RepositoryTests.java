package hello.repo;

import hello.entity.Book;
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
public class RepositoryTests {

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
    public void findAll() {
        assertThat(bookRepository.findAll()).hasSize(3);
    }

    @Test
    public void find() {
        assertThat(bookRepository.findByName("Spring in Action")).isNotNull();
        assertThat(bookRepository.findByName("Spring")).isNull();
    }

    @Test
    public void startsWith() {
        assertThat(bookRepository.findByNameStartsWith("Spring")).hasSize(3);
    }

    @Test
    public void like() {
        assertThat(bookRepository.findByNameContaining("in")).hasSize(3);
    }

    @Test
    public void likeTop5() {
        assertThat(bookRepository.findTop5ByNameContaining("in")).hasSize(3);
    }

}
