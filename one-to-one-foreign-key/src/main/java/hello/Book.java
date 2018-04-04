package hello;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Book {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "book")
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
    }

    @Override
    public String toString() {
        if (null == bookDetail) {
            return String.format("Book [id=%s, name=%s, number of pages=%s]", id, name, "<EMPTY>");
        }

        return String.format("Book [id=%s, name=%s, number of pages=%s]", id, name, bookDetail.getNumberOfPages());
    }

}
