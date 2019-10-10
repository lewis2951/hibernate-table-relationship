package hello.service;

import hello.entity.Book;

import java.util.List;

public interface IBookService {

    void init();

    void deleteAll();

    void delete(Integer id);

    List<Book> findAll();

    List<Book> pageNotNull();

    List<Book> pageGreatThan(int num);

    List<Book> pageBetweenAnd(int min, int max);

    List<Book> like(String name);

}
