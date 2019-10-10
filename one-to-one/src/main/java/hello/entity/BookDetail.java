package hello.entity;

import javax.persistence.*;

@Entity(name = "BOOK_DETAIL")
public class BookDetail {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "NUMBER_OF_PAGES")
    private Integer numberOfPages;

    @OneToOne
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    public BookDetail() {
        super();
    }

    public BookDetail(Integer numberOfPages) {
        super();
        this.numberOfPages = numberOfPages;
    }

}
