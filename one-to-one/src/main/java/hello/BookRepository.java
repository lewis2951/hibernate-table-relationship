package hello;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface BookRepository extends JpaRepository<Book, Integer> {

    Book findByName(String name);

    @Transactional
    void deleteByName(String name);

}
