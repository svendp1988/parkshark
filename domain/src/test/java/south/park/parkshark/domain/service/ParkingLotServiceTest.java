package south.park.parkshark.domain.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import south.park.parkshark.DomainTestApplication;
import south.park.parkshark.datastore.entities.*;
import south.park.parkshark.domain.dto.request.CreateParkingLotDto;
import south.park.parkshark.domain.dto.response.ParkingLotDto;
import south.park.parkshark.domain.exceptions.InvalidEmailException;
import south.park.parkshark.domain.exceptions.LackingEmailAddressException;
import south.park.parkshark.domain.exceptions.LackingPhoneNumberException;
import south.park.parkshark.domain.mappers.MemberMapper;
import south.park.parkshark.domain.mappers.ParkingLotMapper;

import java.util.Collection;
import java.util.List;

//@DataJpaTest
@SpringBootTest(classes = {DomainTestApplication.class})
class ParkingLotServiceTest {

    @Autowired
    ParkingLotService parkingLotService;
    @Autowired
    ParkingLotMapper parkingLotMapper;
    @Autowired
    MemberMapper memberMapper;

    @Test
    void createParkingLot() {
        Address address = new Address("kerkstraat", "2", "3560", "lummen");

        CreateParkingLotDto createParkingLotDto = new CreateParkingLotDto("kerkstraatparking", 200, 1,
                        new Person(address, "dries", "bodaer",
                        List.of(new ContactData(1, ContactTypes.EMAIL, "test@hotmail.com"), new ContactData(1, ContactTypes.FIXEDPHONE, "013624896"))),
                        new Address(), new Division(), new ParkingCategory("underground"));



        ParkingLotDto parkingLotDto = parkingLotService.createParkingLot(createParkingLotDto);
        Collection<ParkingLotDto> allParkingLots = parkingLotService.getAllParkingLots();

        Assertions.assertThat(allParkingLots).hasSize(1);
    }

    @Test
    void invalid_email_throws_invalidemailexeption() {

        Address address = new Address("kerkstraat", "2", "3560", "lummen");

        org.assertj.core.api.Assertions.assertThatExceptionOfType(InvalidEmailException.class).isThrownBy(() ->
        {
            CreateParkingLotDto createParkingLotDto = new CreateParkingLotDto("kerkstraatparking", 200, 1,
                    new Person(address, "dries", "bodaer",
                            List.of(new ContactData(1, ContactTypes.EMAIL, "test"), new ContactData(1, ContactTypes.FIXEDPHONE, "013624896"))),
                    new Address(), new Division(), new ParkingCategory("underground"));

            parkingLotService.createParkingLot(createParkingLotDto);
        });
    }

    @Test
    void lacking_email_throws_lackinEmailexeption() {

        Address address = new Address("kerkstraat", "2", "3560", "lummen");

        org.assertj.core.api.Assertions.assertThatExceptionOfType(LackingEmailAddressException.class).isThrownBy(() ->
        {
            CreateParkingLotDto createParkingLotDto = new CreateParkingLotDto("kerkstraatparking", 200, 1,
                    new Person(address, "dries", "bodaer",
                            List.of( new ContactData(1, ContactTypes.FIXEDPHONE, "013624896"))),
                    new Address(), new Division(), new ParkingCategory("underground"));

            parkingLotService.createParkingLot(createParkingLotDto);
        });
    }

    @Test
    void lacking_Phonenumber_throws_lackinPhonenumberexeption() {

        Address address = new Address("kerkstraat", "2", "3560", "lummen");

        org.assertj.core.api.Assertions.assertThatExceptionOfType(LackingPhoneNumberException.class).isThrownBy(() ->
        {
            CreateParkingLotDto createParkingLotDto = new CreateParkingLotDto("kerkstraatparking", 200, 1,
                    new Person(address, "dries", "bodaer",
                            List.of( new ContactData(1, ContactTypes.EMAIL, "test@gmail.com"))),
                    new Address(), new Division(), new ParkingCategory("underground"));

            parkingLotService.createParkingLot(createParkingLotDto);
        });
    }

}