package south.park.parkshark.war;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import south.park.parkshark.WarTestApplication;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {WarTestApplication.class})
class ParkinglotIntegrationTest {


    @Autowired
    MockMvc mockMvc;





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
