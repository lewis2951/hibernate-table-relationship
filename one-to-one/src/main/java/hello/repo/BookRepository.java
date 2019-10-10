package hello.repo;

import hello.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    Book findByName(String name);

    List<Book> findByNameContaining(String name);

//    List<Book> findBooksByNumberOfPagesNotNull();

    List<Book> findBooksByBookDetail_NumberOfPagesNotNull();

//    List<Book> findBooksByNumberOfPagesGreaterThan(int num);

//    List<Book> findBooksByNumberOfPagesBetweenAnd(int min, int max);

}
