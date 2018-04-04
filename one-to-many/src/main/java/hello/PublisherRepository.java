package hello;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublisherRepository extends JpaRepository<Publisher, Integer> {

    Publisher findByName(String name);

    List<Publisher> findByNameContaining(String name);

}
