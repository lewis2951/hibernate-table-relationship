package hello;

import java.util.List;

public interface IBookService {

    void init();

    void deleteAll();
    void delete(Integer id);

    List<Book> findAll();

}
