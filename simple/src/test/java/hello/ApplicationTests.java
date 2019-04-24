package hello;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    private IBookService bookService;

    @Before
    public void init() {
        bookService.init();
    }

    @After
    public void deleteAll() {
        bookService.deleteAll();
    }

    @Test
    public void delete() {
        int id = bookService.find("Spring in Action").getId();
        bookService.delete(id);

        assertThat(bookService.findAll()).hasSize(2);
    }

    @Test
    public void rename() {
        int id = bookService.find("Spring in Action").getId();
        bookService.rename(id, "Spring in Action 5th");

        assertThat(bookService.like("Spring in Action 5th")).hasSize(1);
    }

    @Test
    public void findAll() {
        assertThat(bookService.findAll()).hasSize(3);
    }

    @Test
    public void find() {
        assertThat(bookService.find("Spring in Action")).isNotNull();
        assertThat(bookService.find("Spring")).isNull();
    }

    @Test
    public void like() {
        assertThat(bookService.like("in")).isNotNull().hasSize(3);
    }

    @Test
    public void startsWith() {
        assertThat(bookService.startsWith("Spring")).isNotNull().hasSize(3);
    }

    @Test
    public void findTop5() {
        assertThat(bookService.findTop5("in")).isNotNull().hasSize(3);
    }

}
