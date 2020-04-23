package south.park.parkshark.api;

import south.park.parkshark.datastore.entities.Division;

//@EnableAutoConfiguration
////@WithMockUser(username="admin")
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {DivisionService.class, DivisionController.class, DivisionMapper.class})
//
//class DivisionControllerTestwebtestClient {
//
//    @Autowired
//    private WebTestClient testClient;
//    @Autowired
//    private DivisionMapper divisionMapper;

//    @Test
//    void createDivision() {
//
//        CreateDivisionDto createDivisionDto = new CreateDivisionDto("dev", "cegeka", "driesb");
//        Division division = divisionMapper.toDivision(createDivisionDto);
//        DivisionDto divisionDto = divisionMapper.toDivisionDto(division);
//
//        testClient.post()
//                .uri("/divisions")
//                .header("Authorization", "Basic " + Base64Utils
//                        .encodeToString(("admin" + ":" + "admin").getBytes(StandardCharsets.UTF_8)))
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(Mono.just(createDivisionDto), CreateDivisionDto.class)
//                .exchange()
//                .expectStatus().isCreated()
//                .expectBody(DivisionDto.class)
//                .isEqualTo(divisionDto);
//    }
//}