package south.park.parkshark;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import south.park.parkshark.domain.dto.request.CreateDivisionDto;
import south.park.parkshark.domain.dto.request.CreateMemberDto;
import south.park.parkshark.domain.dto.response.DivisionDto;
import south.park.parkshark.domain.dto.shared.ContactDataDto;
import south.park.parkshark.domain.dto.shared.LicensePlateDto;
import south.park.parkshark.domain.service.DivisionService;
import south.park.parkshark.domain.service.MemberManagement;
import south.park.parkshark.entities.ContactType;
import south.park.parkshark.entities.ContactTypes;
import south.park.parkshark.entities.MembershipLevels;
import south.park.parkshark.repositories.DivisionRepository;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class IntegrationTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    DivisionService divisionService;
    @Autowired
    MemberManagement memberManagement;

    @Test
    void integration() {
    }

    @WithMockUser
    @Test
    void mockMVC_test() throws Exception {
        CreateDivisionDto createDivisionDto = new CreateDivisionDto();
        String actualResult =
                mockMvc.perform(post("/divisions")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(createDivisionDto))
                ).andExpect(status().isCreated())
                        .andReturn()
                        .getResponse()
                        .getContentAsString();
        String expected = "{\"id\":1,\"name\":null,\"originalName\":null,\"directorName\":null}";
        JSONAssert.assertEquals(expected, actualResult, true);
    }

    @WithMockUser
    @Test
    void mockMVC_test_getAllDivisions() throws Exception {
        CreateDivisionDto createDivisionDto1 = new CreateDivisionDto("D1", "Cegeka", "Dries");
        CreateDivisionDto createDivisionDto2 = new CreateDivisionDto("D2", "Colruyt", "Cemal");
        divisionService.createDivision(createDivisionDto1);
        divisionService.createDivision(createDivisionDto2);
        String actualResult =
                mockMvc.perform(get("/divisions")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                        .andReturn()
                        .getResponse()
                        .getContentAsString();
        String expected = "[{\"id\":1,\"name\":\"D1\",\"originalName\":\"Cegeka\",\"directorName\":\"Dries\"},{\"id\":2,\"name\":\"D2\",\"originalName\":\"Colruyt\",\"directorName\":\"Cemal\"}]";
        JSONAssert.assertEquals(expected, actualResult, true);
    }
    @WithMockUser
    @Test
    void mockMVC_test_getAllMembers() throws Exception {
        List<ContactDataDto> contactDataDtos = List.of(
                new ContactDataDto(ContactTypes.MOBILEPHONE,"0484 78 78 78"),
                new ContactDataDto(ContactTypes.EMAIL,"djflksdjkl@ehb.be")
        );
        LicensePlateDto licensePlateDto = new LicensePlateDto("123","BE");
        CreateMemberDto createMemberDto1 = new CreateMemberDto("HP","Samsing","null","null","null","BE",contactDataDtos, MembershipLevels.GOLD,licensePlateDto);
        memberManagement.registerMember(createMemberDto1);
        String actualResult =
                mockMvc.perform(get("/member")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                        .andReturn()
                        .getResponse()
                        .getContentAsString();
        String expected = "[{\"id\":1,\"name\":\"D1\",\"originalName\":\"Cegeka\",\"directorName\":\"Dries\"}]";
        JSONAssert.assertEquals(expected, actualResult, true);
    }
}