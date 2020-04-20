package south.park.parkshark.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import south.park.parkshark.ParksharkApplication;

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
    public void createPerson(){

    }
}
