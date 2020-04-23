package south.park.parkshark.datastore.repositories;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import south.park.parkshark.datastore.entities.Address;


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
