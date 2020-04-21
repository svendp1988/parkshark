package south.park.parkshark;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import south.park.parkshark.domain.service.DivisionService;
import south.park.parkshark.mappers.DivisionMapper;

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