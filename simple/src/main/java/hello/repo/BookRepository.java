package hello.repo;

import hello.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 自定义查询方法
 */
public interface BookRepository extends JpaRepository<Book, Integer> {

    /* 精确查找 */
    Book findByName(String name);

    /* 模糊查找 */
    List<Book> findByNameStartsWith(String name);

    List<Book> findByNameContaining(String name);

    List<Book> findTop5ByNameContaining(String name);

}
