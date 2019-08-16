package hello.service;

import hello.entity.Book;

import java.util.List;

public interface IBookService {

    /**
     * 初始化
     */
    void init();

    /* 继承自 JpaRepository */

    /**
     * 删除全部
     */
    void deleteAll();

    /**
     * 删除
     *
     * @param id
     */
    void delete(Integer id);

    /**
     * 修改名字
     *
     * @param id
     * @param newName
     */
    void rename(Integer id, String newName);

    /**
     * 查找全部
     *
     * @return
     */
    List<Book> findAll();

    /* BookRepository 自定义 */

    /**
     * 按书名精确查找
     *
     * @param name
     * @return
     */
    Book find(String name);

    /**
     * 按书名模糊查找：以关键字开头的书名
     *
     * @param name
     * @return
     */
    List<Book> startsWith(String name);

    /**
     * 按书名模糊查找
     *
     * @param name
     * @return
     */
    List<Book> like(String name);

    /**
     * 按书名模糊查找：前 5
     *
     * @param name
     * @return
     */
    List<Book> likeTop5(String name);

}
