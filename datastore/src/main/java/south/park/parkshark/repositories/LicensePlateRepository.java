package south.park.parkshark.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import south.park.parkshark.entities.LicensePlate;

@Repository
public interface LicensePlateRepository extends CrudRepository<LicensePlate, Long> {
}
