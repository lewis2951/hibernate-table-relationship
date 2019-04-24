package hello;

import java.util.List;

public interface IBookService {

    void init();

    void deleteAll();
    void delete(Integer id);

    void rename(Integer id, String name);

    List<Book> findAll();
    Book find(String name);
    List<Book> like(String name);
    List<Book> startsWith(String name);
    List<Book> findTop5(String name);

}
