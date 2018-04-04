package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Date;

@Component
public class AppRunner implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public AppRunner(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
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
        findBookByName("in Action");
        findBookByName("Spring in Action");

        // 模糊查找，但不支持忽略大小写、忽略首尾空格
        findBookByNameContaining("action");
        findBookByNameContaining("Action ");
        findBookByNameContaining("Action");

        // --------------------------------------------------
        // 重新初始化，通过bookRepository演示对Book的操作
        // --------------------------------------------------
        reInit();
        display();

        // 更改书名
        margeBook("Spring in Action");
        display();

        // 删除书籍，作者不会被删除
        deleteBook("Spring Boot in Action");
        display();

        // --------------------------------------------------
        // 重新初始化，演示对Author的操作，无需Repository
        // --------------------------------------------------
        reInit();
        display();

        // 清空作者，只是清空关联关系，书籍和作者都还在
        clearAuthor("Spring in Action");
        display();

        // 移除作者，只是移除关联关系，书籍和作者都还在
        removeAuthor("Spring Boot in Action", "Peter");
        removeAuthor("Spring Boot in Action", "Lewis");
        display();

        reInit(); // 重新初始化
        display();

        // 移除全部作者，只是移除关联关系，书籍和作者都还在
        removeAllAuthors("Spring in Action");
        display();

        // --------------------------------------------------
        // 重新初始化，通过authorRepository演示对Author的操作
        // --------------------------------------------------
        reInit();
        display();

        // 追加作者
        plusAuthor("Spring in Action", "Jacob");
        plusAuthor("Spring in Action", "Mark");
        display();

        // 删除作者，啥都删不掉，执行会报异常
//        deleteAuthor("Peter");
//        deleteAuthor("Lewis");

        // 删除全部作者，啥都删不掉，执行会报异常
//        deleteAllAuthors();
    }

    /**
     * 显示
     */
    private void display() {
        logger.info("Display all books & authors ...");

        bookRepository.findAll().forEach(book -> logger.info(book.toString()));
        authorRepository.findAll().forEach(author -> logger.info(author.toString()));
    }

    /**
     * 初始化
     */
    private void init() {
        logger.info("Initial 2 books with 3 authors & 4 relationship ...");

        Book spring = new Book("Spring in Action");
        Book springboot = new Book("Spring Boot in Action");

        Author lewis = new Author("Lewis");
        Author mark = new Author("Mark");
        Author peter = new Author("Peter");

        BookAuthor springLewis = new BookAuthor(spring, lewis, new Date());
        BookAuthor springMark = new BookAuthor(spring, mark, new Date());

        BookAuthor springbootLewis = new BookAuthor(springboot, lewis, new Date());
        BookAuthor springbootPeter = new BookAuthor(springboot, peter, new Date());

        spring.getBookAuthors().addAll(Arrays.asList(springLewis, springMark));
        springboot.getBookAuthors().addAll(Arrays.asList(springbootLewis, springbootPeter));

        authorRepository.saveAll(Arrays.asList(lewis, mark, peter));
        bookRepository.saveAll(Arrays.asList(spring, springboot));
    }

    /**
     * 重新初始化
     */
    private void reInit() {
        deleteAllBooks();
        deleteAllAuthors();
        init();
    }

    /**
     * 删除全部（书籍和关联关系会被删除，作者不会被删除）
     */
    private void deleteAllBooks() {
        logger.info("Delete all books ...");

        bookRepository.deleteAll();
    }

    /**
     * 精确查找
     *
     * @param name 书名
     */
    private void findBookByName(String name) {
        logger.info("findBookByName [name:{}] ...", name);

        Book book = bookRepository.findByName(name);
        if (null == book) {
            logger.info(String.format("Book [%s]", "<empty>"));
        } else {
            logger.info(book.toString());
        }
    }

    /**
     * 模糊查找
     *
     * @param name 书名
     */
    private void findBookByNameContaining(String name) {
        logger.info("findBookByNameContaining [name:{}] ...", name);

        bookRepository.findByNameContaining(name).forEach(book -> logger.info(book.toString()));
    }

    /**
     * 修改
     *
     * @param name 书名
     */
    private void margeBook(String name) {
        logger.info("margeBook [name:{}] ...", name);

        Book book = bookRepository.findByName(name);
        if (null == book) {
            return;
        }

        book.setName(name + " (4th Edition)");
    }

    /**
     * 删除（书籍和关联关系被删除，作者不会被删除）
     *
     * @param name 书名
     */
    private void deleteBook(String name) {
        logger.info("deleteBook [name:{}]", name);

        Book book = bookRepository.findByName(name);
        if (null == book) {
            return;
        }

        bookRepository.delete(book);
    }

    /**
     * 清空某书的作者
     *
     * @param bookName 书名
     */
    private void clearAuthor(String bookName) {
        logger.info("clearAuthor [book.name:{}]", bookName);

        Book book = bookRepository.findByName(bookName);
        if (null == book) {
            return;
        }

        book.getBookAuthors().clear();
    }

    /**
     * 移除某书的作者
     *
     * @param bookName   书名
     * @param authorName 作者
     */
    private void removeAuthor(String bookName, String authorName) {
        logger.info("removeAuthor [book.name:{}, author.name:{}] ...", bookName, authorName);

        Book book = bookRepository.findByName(bookName);
        if (null == book) {
            return;
        }

        Author author = authorRepository.findByName(authorName);
        if (null == author) {
            return;
        }

        book.getBookAuthors().removeIf(bookAuthor -> bookAuthor.getAuthor().equals(author));
    }

    /**
     * 移除某书的全部作者
     *
     * @param bookName 书名
     */
    private void removeAllAuthors(String bookName) {
        logger.info("removeAllAuthors [book.name:{}]", bookName);

        Book book = bookRepository.findByName(bookName);
        if (null == book) {
            return;
        }

        book.getBookAuthors().removeAll(book.getBookAuthors());
    }

    /**
     * 为某书追加作者
     *
     * @param bookName   书名
     * @param authorName 作者
     */
    private void plusAuthor(String bookName, String authorName) {
        logger.info("plusAuthor [book.name:{}, author.name:{}] ...", bookName, authorName);

        Book book = bookRepository.findByName(bookName);
        if (null == book) {
            return;
        }

        Author author = authorRepository.findByName(authorName);
        if (null != author) {
            return;
        }

        author = new Author(authorName);
        BookAuthor bookAuthor = new BookAuthor(book, author, new Date());
        book.getBookAuthors().add(bookAuthor);
        authorRepository.save(author);
    }

    /**
     * 删除，只删除作者，如果被删除的作者和书籍有关联关系，则删除会报错
     *
     * @param name 作者
     */
    private void deleteAuthor(String name) {
        logger.info("deleteAuthor [name:{}] ...", name);

        Author author = authorRepository.findByName(name);
        authorRepository.delete(author);
    }

    /**
     * 删除全部，只删除作者，如果被删除的作者和书籍有关联关系，则删除会报错
     */
    private void deleteAllAuthors() {
        logger.info("Delete all authors ...");

        authorRepository.deleteAll();
    }

}
