package south.park.parkshark.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import south.park.parkshark.entities.Division;

import java.util.List;

@Repository
public interface DivisionRepository extends CrudRepository<Division, Long> {

    @Override
    List<Division> findAll();
}
