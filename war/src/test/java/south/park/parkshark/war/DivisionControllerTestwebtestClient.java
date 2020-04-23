package south.park.parkshark.war;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.Base64Utils;
import reactor.core.publisher.Mono;
import south.park.parkshark.WarTestApplication;
import south.park.parkshark.api.DivisionController;
import south.park.parkshark.domain.dto.request.CreateDivisionDto;
import south.park.parkshark.domain.dto.response.DivisionDto;
import south.park.parkshark.domain.service.DivisionService;
import south.park.parkshark.datastore.entities.Division;
import south.park.parkshark.domain.mappers.DivisionMapper;

import java.nio.charset.StandardCharsets;

@EnableAutoConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {WarTestApplication.class})
class DivisionControllerTestwebtestClient {

    @Autowired
    private WebTestClient testClient;
    @Autowired
    private DivisionMapper divisionMapper;

    @Disabled // TODO: fix security issues
    @Test
    void createDivision() {

        CreateDivisionDto createDivisionDto = new CreateDivisionDto("dev", "cegeka", "driesb");
        Division division = divisionMapper.toDivision(createDivisionDto);
        DivisionDto divisionDto = divisionMapper.toDivisionDto(division);

        testClient.post()
                .uri("/divisions")
                .header("Authorization", "Basic " + Base64Utils
                        .encodeToString(("admin" + ":" + "admin").getBytes(StandardCharsets.UTF_8)))
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(createDivisionDto), CreateDivisionDto.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(DivisionDto.class)
                .isEqualTo(divisionDto);
    }
}
