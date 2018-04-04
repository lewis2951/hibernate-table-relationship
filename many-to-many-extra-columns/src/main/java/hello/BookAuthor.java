package hello;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity(name = "BOOK_AUTHOR")
public class BookAuthor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @ManyToOne
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    @Id
    @ManyToOne
    @JoinColumn(name = "AUTHOR_ID")
    private Author author;

    @Column(name = "FINISH_DATE")
    private Date finishDate;

    public BookAuthor() {
        super();
    }

    public BookAuthor(Book book, Author author) {
        super();
        this.book = book;
        this.author = author;
    }

    public BookAuthor(Book book, Author author, Date finishDate) {
        super();
        this.book = book;
        this.author = author;
        this.finishDate = finishDate;
    }

    @Override
    public String toString() {
        return String.format("BookAuthor [book=%s, author=%s, finishDate=%s]", book.getName(), author.getName(), finishDate);
    }

}
