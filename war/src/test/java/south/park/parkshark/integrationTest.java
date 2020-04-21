package south.park.parkshark;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import south.park.parkshark.domain.dto.request.CreateDivisionDto;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class integrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void integration() {
    }

    @WithMockUser
    @Test
    void mockMVC_test() throws Exception {
        CreateDivisionDto createDivisionDto = new CreateDivisionDto();

        mockMvc.perform(post("/divisions")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content( new ObjectMapper().writeValueAsString(createDivisionDto))
                ).andExpect(status().isCreated());
    }

}