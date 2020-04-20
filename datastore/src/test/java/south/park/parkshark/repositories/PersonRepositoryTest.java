package south.park.parkshark.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import south.park.parkshark.ParksharkApplication;
import south.park.parkshark.entities.Address;
import south.park.parkshark.entities.ContactData;
import south.park.parkshark.entities.ContactTypes;
import south.park.parkshark.entities.Person;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@DataJpaTest
@AutoConfigureTestDatabase
@ContextConfiguration(classes = {ParksharkApplication.class})
public class PersonRepositoryTest {
    private final PersonRepository personRepository;
    private final ContactDataRepository contactDataRepository;

    @Autowired
    PersonRepositoryTest(PersonRepository personRepository, ContactDataRepository contactDataRepository){
        this.personRepository = personRepository;
        this.contactDataRepository = contactDataRepository;
    }

    @Test
    @Sql("defaultAddress.sql")
    public void createPerson(){
        Address address = new Address(1,"here street","33", "1000", "Bruxelles");
        Person person = new Person(1L,address,"Bob","Bobson",List.of());

        personRepository.save(person);

        Person actual = personRepository.findById(1L).get();

        assertThat(actual).isEqualTo(person);

    }

    @Test
    @Sql("defaultAddress.sql")
    public void personCanHaveContactData() {
        List<ContactData> contactData = List.of(
                new ContactData(1,1L, ContactTypes.EMAIL,"bob@thebobsons.com"));
        Address address = new Address(1,"here street","33", "1000", "Bruxelles");
        Person person = new Person(1L,address,"Bob","Bobson", List.of());

        person = personRepository.save(person);
        contactDataRepository.saveAll(contactData);
        person.setContactData(contactData);
        Person actual = personRepository.findById(1L).get();

        assertThat(actual.getContactDataList()).hasSameElementsAs(contactData);
    }
}