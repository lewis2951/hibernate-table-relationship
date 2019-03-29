package hello;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    Book findByName(String name);

    List<Book> findByNameContaining(String name);

    List<Book> findByNameStartsWith(String name);

    List<Book> findTop5ByNameContaining(String name);

}
