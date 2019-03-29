package hello;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity(name = "BOOK_DETAIL")
public class BookDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "NUMBER_OF_PAGES")
    private Integer numberOfPages;

    @Id
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
