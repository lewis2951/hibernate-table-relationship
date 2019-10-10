package hello.entity;

import javax.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    //            (cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "book")
    @OneToOne(cascade = CascadeType.PERSIST, mappedBy = "book", fetch = FetchType.LAZY)

    @PrimaryKeyJoinColumn
    private BookDetail bookDetail;

    public Book() {
        super();
    }

    public Book(String name) {
        super();
        this.name = name;
    }

    public Book(String name, BookDetail bookDetail) {
        super();
        this.name = name;
        this.bookDetail = bookDetail;
        this.bookDetail.setBook(this);
    }

}
