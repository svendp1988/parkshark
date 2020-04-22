package south.park.parkshark;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import south.park.parkshark.domain.dto.request.CreateParkingLotDto;
import south.park.parkshark.domain.dto.response.ParkingLotDto;
import south.park.parkshark.domain.service.ParkingLotService;
import south.park.parkshark.entities.Address;
import south.park.parkshark.entities.Division;
import south.park.parkshark.entities.ParkingCategory;
import south.park.parkshark.entities.Person;
import south.park.parkshark.mappers.ParkingLotMapper;
import south.park.parkshark.repositories.ParkingLotRepository;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ContextConfiguration(classes = {ParkingLotService.class, ParkingLotMapper.class})
@EnableAutoConfiguration
@AutoConfigureTestDatabase
class ParkingLotServiceTest {

    @Autowired
    ParkingLotService parkingLotService;
    @Autowired
    ParkingLotMapper parkingLotMapper;

    @Test
    void createParkingLot() {
        CreateParkingLotDto createParkingLotDto = new CreateParkingLotDto("kerkstraatparking", 200, 1, new Person(), new Address(), new Division(), new ParkingCategory("underground"));
        ParkingLotDto parkingLotDto = parkingLotService.createParkingLot(createParkingLotDto);
        Collection<ParkingLotDto> allParkingLots = parkingLotService.getAllParkingLots();

        org.assertj.core.api.Assertions.assertThat(allParkingLots).contains(parkingLotDto);
    }
}