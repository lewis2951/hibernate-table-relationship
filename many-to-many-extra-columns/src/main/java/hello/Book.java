package hello;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
public class Book {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    // 设置orphanRemoval=true时，会将FK上NULL对应的数据都会删掉
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BookAuthor> bookAuthors;

    public Book() {
        super();
    }

    public Book(String name) {
        super();
        this.name = name;
        bookAuthors = new HashSet<>();
    }

}
