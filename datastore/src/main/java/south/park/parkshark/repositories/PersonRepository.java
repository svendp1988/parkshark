package south.park.parkshark.repositories;

import org.springframework.data.repository.CrudRepository;
import south.park.parkshark.entities.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
