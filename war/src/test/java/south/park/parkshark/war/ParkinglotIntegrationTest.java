package south.park.parkshark.war;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import south.park.parkshark.WarTestApplication;
import south.park.parkshark.datastore.entities.*;
import south.park.parkshark.domain.dto.request.CreateParkingLotDto;
import south.park.parkshark.domain.dto.response.ParkingLotDto;
import south.park.parkshark.domain.mappers.ParkingLotMapper;

import java.util.List;

import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockUser;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.springSecurity;
import static org.springframework.web.reactive.function.client.ExchangeFilterFunctions.basicAuthentication;

@AutoConfigureWebTestClient
@EnableAutoConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {WarTestApplication.class})
class ParkinglotIntegrationTest {

    @Autowired
    WebTestClient webTestClient;
    @Autowired
    ParkingLotMapper parkingLotMapper;
    @Autowired
    ApplicationContext context;

    @BeforeEach
    public void setup() {
        webTestClient = WebTestClient
                .bindToApplicationContext(this.context)
                // add Spring Security test Support
                .apply(springSecurity())
//                .configureClient()
//                .filter(basicAuthentication())
                .build();
    }

    @Disabled
    @Test
    void integration() {
        Address personAddress = new Address("kerkstraat", "2", "3560", "lummen");
        Person contactPerson = new Person(personAddress, "dries", "bodaer", List.of( new ContactData(1, ContactTypes.EMAIL, "test@gmail.com"), new ContactData(1, ContactTypes.FIXEDPHONE, "1350313103")));
        Address parkinglotAddress = new Address("cherchstraat", "2", "3560", "lummen");
        Division division = new Division("dev1", "division", "haroldB");
        ParkingCategory parkingCategory = new ParkingCategory("underground");
        CreateParkingLotDto createParkingLotDto = new CreateParkingLotDto("djamalparking", 300, 2, contactPerson, parkinglotAddress, division, parkingCategory);
        ParkingLot parkingLot = parkingLotMapper.toParkingLot(createParkingLotDto);
        ParkingLotDto parkingLotDto = parkingLotMapper.toParkingLotDto(parkingLot);

        webTestClient.mutateWith(mockUser().roles("ADMIN"))
                .post().contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just( createParkingLotDto), CreateParkingLotDto.class)
                .exchange()
                .expectBody(ParkingLotDto.class)
                .isEqualTo(parkingLotDto);
    }
}
