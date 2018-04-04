package hello;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = "publisher")
@ToString(exclude = "publisher")
@Entity
public class Book {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "PUBLISHER_ID")
    private Publisher publisher;

    public Book() {
        super();
    }

    public Book(String name) {
        super();
        this.name = name;
    }

    public Book(String name, Publisher publisher) {
        super();
        this.name = name;
        this.publisher = publisher;
    }

}
