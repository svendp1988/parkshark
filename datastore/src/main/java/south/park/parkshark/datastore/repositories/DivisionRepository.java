package south.park.parkshark.datastore.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import south.park.parkshark.datastore.entities.Division;

import java.util.List;

@Repository
public interface DivisionRepository extends CrudRepository<Division, Long> {

    @Override
    List<Division> findAll();
}
