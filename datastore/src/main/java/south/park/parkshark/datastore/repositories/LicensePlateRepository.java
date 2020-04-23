package south.park.parkshark.datastore.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import south.park.parkshark.datastore.entities.LicensePlate;

@Repository
public interface LicensePlateRepository extends CrudRepository<LicensePlate, Long> {
}
