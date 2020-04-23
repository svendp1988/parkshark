package south.park.parkshark.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import south.park.parkshark.domain.dto.request.CreateMemberDto;
import south.park.parkshark.domain.dto.shared.LicensePlateDto;
import south.park.parkshark.domain.exceptions.InvalidEmailException;
import south.park.parkshark.domain.service.MemberManagement;
import south.park.parkshark.datastore.entities.MembershipLevels;

import java.util.List;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(MemberController.class)
class MemberControllerTest {

    @MockBean
    private MemberManagement memberManagement;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    MemberController memberController;

    @Disabled // TODO: fix the security issue
    @Test
    @WithMockUser
    void invalidEmailException() throws Exception {
        CreateMemberDto createMemberDto = new CreateMemberDto("firstName", "lastName", "streetName", "streetNumber", "postalCode", "postalLabel", List.of(), MembershipLevels.BRONZE, new LicensePlateDto("plateNumber", "issuingCountry"));
        when(memberManagement.registerMember(any(CreateMemberDto.class))).thenThrow(InvalidEmailException.class);
//        memberController.registerAsMember(createMemberDto);
        String errorMessage = mockMvc
                .perform(post("/member)")
                        .with(csrf())
                        .content(new ObjectMapper().writeValueAsString(createMemberDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse().getErrorMessage();
        Assertions.assertEquals(errorMessage, "");
    }
}