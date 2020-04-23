package south.park.parkshark.datastore.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import south.park.parkshark.datastore.entities.Member;
import south.park.parkshark.datastore.entities.ParkingLot;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@DataJpaTest
@AutoConfigureTestDatabase
@ComponentScan(basePackages = "south.park.parkshark")
class ParkingLotRepositoryTest {
    @Autowired
    ParkingLotRepository parkingLotRepository;

    @Test
    public void weExist(){
        assertThat(parkingLotRepository).isNotNull();
    }

    @Test
    @Sql("createParkingLotDependencies.sql")
    public void getAllMembers() {
        List<ParkingLot> actualParkingLots = parkingLotRepository.findAll();
        ParkingLot actualParkingLotById = parkingLotRepository.findById(2L).get();
        assertThat(actualParkingLots).hasSize(3);
        assertThat(actualParkingLots).contains(actualParkingLotById);
    }

}