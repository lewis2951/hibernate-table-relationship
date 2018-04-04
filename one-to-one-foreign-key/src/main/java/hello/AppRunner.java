package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class AppRunner implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

    private final BookRepository bookRepository;

    public AppRunner(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        // save a couple of books
        Book bookA = new Book("Book A", new BookDetail(218));
        Book bookB = new Book("Book B", new BookDetail(256));
        Book bookC = new Book("Book C", new BookDetail(183));
        Book bookD = new Book("Book D");
        bookRepository.saveAll(Arrays.asList(bookA, bookB, bookC, bookD));

        // fetch all books
        bookRepository.findAll().forEach(book -> logger.info(book.toString()));

        // delete all books
        bookRepository.deleteAll();
    }

}
