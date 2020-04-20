package south.park.parkshark;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import south.park.parkshark.dtos.CreateDivisionDto;
import south.park.parkshark.dtos.DivisionDto;
import south.park.parkshark.entities.Division;
import south.park.parkshark.mappers.DivisionMapper;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@EnableAutoConfiguration
@AutoConfigureTestDatabase
@DataJpaTest
@ContextConfiguration(classes = {DivisionService.class, DivisionMapper.class})
class DivisionServiceTest {

    @Autowired
    DivisionService divisionService;

    @Test
    void Create_Division() {
        CreateDivisionDto divisionToTest = new CreateDivisionDto("dev1", "cegeka", "driesb");
        DivisionDto divisionDto = divisionService.createDivision(divisionToTest);
        Iterable<DivisionDto> actual = divisionService.getAllDivisions();

        assertThat( actual).contains(divisionDto);
    }

    @Test
    void get_all_division() {
        CreateDivisionDto divisionToTest1 = new CreateDivisionDto("dev1", "cegeka", "driesb");
        CreateDivisionDto divisionToTest2 = new CreateDivisionDto("dev2", "cegeka", "driesb");
        CreateDivisionDto divisionToTest3 = new CreateDivisionDto("dev3", "cegeka", "driesb");
        CreateDivisionDto divisionToTest4 = new CreateDivisionDto("dev4", "cegeka", "driesb");
        DivisionDto division1 = divisionService.createDivision(divisionToTest1);
        DivisionDto division2 = divisionService.createDivision(divisionToTest2);
        DivisionDto division3 = divisionService.createDivision(divisionToTest3);
        DivisionDto division4 = divisionService.createDivision(divisionToTest4);

        Collection<DivisionDto> actualList = divisionService.getAllDivisions();
        List<DivisionDto> listToTest = List.of(division1, division2, division3, division4);

        assertThat(actualList).hasSize(4).containsAll(listToTest);
    }
}