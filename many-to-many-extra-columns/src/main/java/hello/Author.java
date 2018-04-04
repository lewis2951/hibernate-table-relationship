package hello;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Getter
@Setter
@ToString(exclude = "bookAuthors")
@Entity
public class Author {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "author")
    private Set<BookAuthor> bookAuthors;

    public Author() {
        super();
    }

    public Author(String name) {
        super();
        this.name = name;
    }

}
