package south.park.parkshark;


//@WebMvcTest(DivisionController.class)
//class DivisionControllerTest {
//
//    @MockBean
//    DivisionService divisionService;
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @Test
//    void createDivision() throws Exception {
//        CreateDivisionDto createDivisionDto = new CreateDivisionDto("dev1", "cegeka", "driesb");
//
//        mockMvc.perform(post("/divisions")
//
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(createDivisionDto)))
//                .andExpect(status().isCreated());
//
//    }
//
//    public static String asJsonString(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}