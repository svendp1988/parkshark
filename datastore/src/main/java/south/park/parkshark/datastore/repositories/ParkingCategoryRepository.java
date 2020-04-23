package south.park.parkshark.datastore.repositories;

import org.springframework.data.repository.CrudRepository;
import south.park.parkshark.datastore.entities.ParkingCategory;

public interface ParkingCategoryRepository extends CrudRepository<ParkingCategory, Long> {
}
