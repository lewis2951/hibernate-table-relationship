package hello;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Book {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    public Book() {
        super();
    }

    public Book(String name) {
        super();
        this.name = name;
    }

}
