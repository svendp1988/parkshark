package south.park.parkshark.datastore.repositories;

import org.springframework.data.repository.CrudRepository;
import south.park.parkshark.datastore.entities.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
