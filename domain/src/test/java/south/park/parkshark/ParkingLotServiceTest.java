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
import south.park.parkshark.domain.exceptions.InvalidEmailException;
import south.park.parkshark.domain.exceptions.LackingEmailAddressException;
import south.park.parkshark.domain.exceptions.LackingPhoneNumberException;
import south.park.parkshark.domain.service.ParkingLotService;
import south.park.parkshark.domain.service.ValidationService;
import south.park.parkshark.entities.*;
import south.park.parkshark.mappers.MemberMapper;
import south.park.parkshark.mappers.ParkingLotMapper;
import south.park.parkshark.repositories.ParkingLotRepository;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ContextConfiguration(classes = {ParkingLotService.class, ParkingLotMapper.class, ValidationService.class, MemberMapper.class})
@EnableAutoConfiguration
@AutoConfigureTestDatabase
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

        org.assertj.core.api.Assertions.assertThat(allParkingLots).contains(parkingLotDto);
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