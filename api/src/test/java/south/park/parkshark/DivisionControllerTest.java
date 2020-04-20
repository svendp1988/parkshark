package south.park.parkshark;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import south.park.parkshark.dtos.CreateDivisionDto;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


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