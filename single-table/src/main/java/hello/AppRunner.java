package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;

@Component
public class AppRunner implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

    private final BookRepository bookRepository;

    public AppRunner(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    @Override
    public void run(ApplicationArguments args) {
        // --------------------------------------------------
        // 初始化
        // --------------------------------------------------
        init();
        display();

        // 精确查找
        findByName("in Action");
        findByName("Spring in Action");

        // 模糊查找，但不支持忽略大小写、忽略首尾空格
        findByNameContaining("boot");
        findByNameContaining("Boot");
        findByNameContaining("in Action");

        // 修改
        marge("Spring in Action");
        display();

        // 删除
        delete("Spring Data in Action");
        display();

        // 删除全部
        deleteAll();
        display();
    }

    /**
     * 显示
     */
    private void display() {
        logger.info("Display all books ...");

        bookRepository.findAll().forEach(book -> logger.info(book.toString()));
    }

    /**
     * 初始化
     */
    private void init() {
        logger.info("Initial 3 books ...");

        Book bookA = new Book("Spring in Action");
        Book bookB = new Book("Spring Boot in Action");
        Book bookC = new Book("Spring Data in Action");
        bookRepository.saveAll(Arrays.asList(bookA, bookB, bookC));
    }

    /**
     * 精确查找
     *
     * @param name 书名
     */
    private void findByName(String name) {
        logger.info("findByName [name:{}] ...", name);

        Book book = bookRepository.findByName(name);
        if (null == book) {
            logger.info("Book [{}]", "<empty>");
        } else {
            logger.info(book.toString());
        }
    }

    /**
     * 模糊查找
     *
     * @param name 书名
     */
    private void findByNameContaining(String name) {
        logger.info("findByNameContaining [name:{}] ...", name);

        bookRepository.findByNameContaining(name).forEach(book -> logger.info(book.toString()));
    }

    /**
     * 修改
     *
     * @param name 书名
     */
    private void marge(String name) {
        logger.info("marge [name:{}] ...", name);

        Book book = bookRepository.findByName(name);
        if (null == book) {
            return;
        }

        book.setName(name + " (5th Edition)");
        bookRepository.save(book);
    }

    /**
     * 删除
     *
     * @param name 书名
     */
    private void delete(String name) {
        logger.info("delete [name:{}] ...", name);

        Book book = bookRepository.findByName(name);
        if (null == book) {
            return;
        }

        bookRepository.delete(book);
    }

    /**
     * 删除全部
     */
    private void deleteAll() {
        logger.info("Delete All Books ...");

        bookRepository.deleteAll();
    }

}
