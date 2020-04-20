package south.park.parkshark;

import com.jayway.jsonpath.internal.function.numeric.Sum;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.Base64Utils;
import reactor.core.publisher.Mono;
import south.park.parkshark.dtos.CreateDivisionDto;
import south.park.parkshark.mappers.DivisionMapper;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

@EnableAutoConfiguration
//@AutoConfigureTestDatabase
//@DataJpaTest
//@ContextConfiguration(classes = {DivisionService.class, DivisionMapper.class})

@RunWith(SpringRunner.class)
@WithMockUser(username="admin")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {DivisionService.class, DivisionController.class, DivisionMapper.class})

class DivisionControllerTestwebtestClient {

    @Autowired
    private WebTestClient testClient;

//    @Test
//    void createDivision() {
//
//        CreateDivisionDto createDivisionDto = new CreateDivisionDto("dev", "cegeka", "driesb");
//
//        testClient.post()
//                .uri("/divisions")
//                .header("Authorization", "Basic " + Base64Utils
//                        .encodeToString(("admin" + ":" + "admin").getBytes(StandardCharsets.UTF_8)))
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(Mono.just(createDivisionDto), CreateDivisionDto.class)
//                .exchange()
//                .expectStatus().isCreated();
//    }
}