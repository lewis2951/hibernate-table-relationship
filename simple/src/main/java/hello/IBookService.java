package hello;

import java.util.List;

public interface IBookService {

    void init();

    void rename(Integer id, String name);

    void deleteAll();
    void delete(Integer id);

    List<Book> findAll();
    Book find(String name);
    List<Book> like(String name);
    List<Book> startsWith(String name);
    List<Book> findTop5(String name);

}
