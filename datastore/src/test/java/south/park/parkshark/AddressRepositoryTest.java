package south.park.parkshark;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import south.park.parkshark.entities.Address;
import south.park.parkshark.repositories.AddressRepository;


//@AutoConfigureTestDatabase
@DataJpaTest
//@ComponentScan(basePackages = "south.park.parkshark")
//@SpringBootTest(classes = AddressRepository.class)
//@ContextConfiguration
public class AddressRepositoryTest {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressRepositoryTest(AddressRepository addressRepository){
        this.addressRepository = addressRepository;
    }

    @Test
    public void createAddress(){
        Address address = new Address(1,"here street", "1", "11110", "SomeCity");

        addressRepository.save(address);

        Address actual = addressRepository.findById(1L).get();

        Assertions.assertThat(actual.getAddressId()).isEqualTo(1L);
        Assertions.assertThat(actual.getPostalLabel()).isEqualTo("SomeCity");
    }
}
