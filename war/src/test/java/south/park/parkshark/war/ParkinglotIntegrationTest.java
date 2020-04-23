package south.park.parkshark.war;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.Base64Utils;
import reactor.core.publisher.Mono;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import south.park.parkshark.WarTestApplication;
import south.park.parkshark.datastore.entities.*;
import south.park.parkshark.domain.dto.request.CreateParkingLotDto;
import south.park.parkshark.domain.dto.response.ParkingLotDto;
import south.park.parkshark.domain.mappers.ParkingLotMapper;

import java.nio.charset.StandardCharsets;
import java.util.List;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockUser;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.springSecurity;
import static org.springframework.web.reactive.function.client.ExchangeFilterFunctions.basicAuthentication;

@AutoConfigureMockMvc
@AutoConfigureWebTestClient
@EnableAutoConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {WarTestApplication.class})
class ParkinglotIntegrationTest {

    @Autowired
    WebTestClient webTestClient;
    @Autowired
    ParkingLotMapper parkingLotMapper;

    @Autowired
    MockMvc mockMvc;

    @Disabled
    @Test
    void integration() {
        Address personAddress = new Address("kerkstraat", "2", "3560", "lummen");
        Address personAddressSameData = new Address(1,"kerkstraat", "2", "3560", "lummen");

        Person contactPerson = new Person(personAddress, "dries", "bodaer", List.of( new ContactData(1, ContactTypes.EMAIL, "test@gmail.com"), new ContactData(1, ContactTypes.FIXEDPHONE, "1350313103")));
        Person contactPersonSameData = new Person( 1L,personAddressSameData, "dries", "bodaer", List.of( new ContactData(1L, 1, ContactTypes.EMAIL, "test@gmail.com"), new ContactData(2L, 1, ContactTypes.FIXEDPHONE, "1350313103")));
        Address parkinglotAddress = new Address("cherchstraat", "2", "3560", "lummen");
        Address parkinglotAddressSameData = new Address(2,"cherchstraat", "2", "3560", "lummen");
        Division division = new Division("dev1", "division", "haroldB");
        Division divisionSameData = new Division(1L, "dev1", "division","haroldB", 1L);
        ParkingCategory parkingCategory = new ParkingCategory("underground");
        ParkingCategory parkingCategorySameData = new ParkingCategory(1 , "underground");
        CreateParkingLotDto createParkingLotDto = new CreateParkingLotDto("djamalparking", 300, 2, contactPerson, parkinglotAddress, division, parkingCategory);
        ParkingLot parkingLot = parkingLotMapper.toParkingLot(createParkingLotDto);
        ParkingLotDto parkingLotDto = parkingLotMapper.toParkingLotDto(parkingLot);
        ParkingLotDto parkinglotDtoSameData = new ParkingLotDto(1,"djamalparking", 300, 2, contactPersonSameData, parkinglotAddressSameData, divisionSameData, parkingCategorySameData);

        webTestClient.post().uri("/parkinglot").contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just( createParkingLotDto), CreateParkingLotDto.class)
                .header("Authorization", "Basic " + Base64Utils
                        .encodeToString(("manager" + ":" + "manager").getBytes(StandardCharsets.UTF_8)))
                .exchange()
                .expectStatus()
                .isCreated();
    }

    @WithMockUser
    @Test
    void getAllParkingLots() throws Exception {

        String actualResult =
                mockMvc.perform(MockMvcRequestBuilders.get("/parkinglot")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                        .andReturn()
                        .getResponse()
                        .getContentAsString();
        String expected = "[{\"id\":1,\"name\":\"NAME\",\"maxCapacity\":1500,\"pricePerHour\":15,\"contactPerson\":null,\"address\":{\"addressId\":1,\"streetName\":\"here street\",\"streetNumber\":\"33\",\"postalCode\":\"1000\",\"postalLabel\":\"Bruxelles\"},\"division\":{\"id\":1,\"name\":\"name\",\"originalName\":\"originalName\",\"directorName\":\"directorFullName\",\"parentId\":null},\"parkingCategory\":{\"id\":1,\"name\":\"BELOW_GROUND\"}},{\"id\":2,\"name\":\"NAME\",\"maxCapacity\":1500,\"pricePerHour\":15,\"contactPerson\":null,\"address\":{\"addressId\":1,\"streetName\":\"here street\",\"streetNumber\":\"33\",\"postalCode\":\"1000\",\"postalLabel\":\"Bruxelles\"},\"division\":{\"id\":1,\"name\":\"name\",\"originalName\":\"originalName\",\"directorName\":\"directorFullName\",\"parentId\":null},\"parkingCategory\":{\"id\":1,\"name\":\"BELOW_GROUND\"}},{\"id\":3,\"name\":\"NAME\",\"maxCapacity\":1500,\"pricePerHour\":15,\"contactPerson\":null,\"address\":{\"addressId\":1,\"streetName\":\"here street\",\"streetNumber\":\"33\",\"postalCode\":\"1000\",\"postalLabel\":\"Bruxelles\"},\"division\":{\"id\":1,\"name\":\"name\",\"originalName\":\"originalName\",\"directorName\":\"directorFullName\",\"parentId\":null},\"parkingCategory\":{\"id\":1,\"name\":\"BELOW_GROUND\"}}]";
        JSONAssert.assertEquals(expected, actualResult, true);
    }

}
