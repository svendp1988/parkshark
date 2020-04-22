package south.park.parkshark.repositories;

import org.springframework.data.repository.CrudRepository;
import south.park.parkshark.entities.ParkingCategory;

public interface ParkingCategoryRepository extends CrudRepository<ParkingCategory, Long> {
}
