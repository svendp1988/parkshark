package south.park.parkshark.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import south.park.parkshark.entities.ParkingLot;

import java.util.List;

@Repository
public interface ParkingLotRepository extends CrudRepository<ParkingLot, Long> {

    @Override
    List<ParkingLot> findAll();
}
