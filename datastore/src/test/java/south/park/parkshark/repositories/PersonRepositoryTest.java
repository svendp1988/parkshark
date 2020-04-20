package south.park.parkshark.repositories;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import south.park.parkshark.ParksharkApplication;
import south.park.parkshark.entities.Address;
import south.park.parkshark.entities.ContactData;
import south.park.parkshark.entities.ContactTypes;
import south.park.parkshark.entities.Person;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase
@ContextConfiguration(classes = {ParksharkApplication.class})
public class PersonRepositoryTest {
    private final PersonRepository personRepository;

    @Autowired
    PersonRepositoryTest(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    @Test
    @Sql("createPersonDependencies.sql")
    public void createPerson(){
        Address address = new Address(1,"here street","33", "1000", "Bruxelles");
        List<ContactData> contactData = List.of(
                new ContactData(1,1L, ContactTypes.EMAIL,"bob@thebobsons.com"));
        Person person = new Person(1L,address,"Bob","Bobson",List.of());

        personRepository.save(person);

        Person actual = personRepository.findById(1L).get();

        Assertions.assertThat(actual).isEqualTo(person);

    }
}
