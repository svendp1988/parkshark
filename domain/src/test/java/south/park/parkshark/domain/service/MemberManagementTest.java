package south.park.parkshark.domain.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import south.park.parkshark.domain.dto.shared.ContactDataDto;
import south.park.parkshark.domain.dto.request.CreateMemberDto;
import south.park.parkshark.domain.dto.response.MemberDto;
import south.park.parkshark.domain.dto.shared.LicensePlateDto;
import south.park.parkshark.entities.ContactTypes;
import south.park.parkshark.entities.MembershipLevels;
import south.park.parkshark.repositories.MemberRepository;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberManagementTest {
    private MemberManagement memberManagement;
    private MemberMapper memberMapper = new MemberMapper();

    @MockBean
    private MemberRepository memberRepository;

    public MemberManagementTest() {
        this.memberManagement = new MemberManagement(memberRepository, memberMapper);
    }

    @Test
    void registerAsMember_returnsADto() {
        List<ContactDataDto> contactData = List.of(new ContactDataDto(ContactTypes.EMAIL,"bob@bobson.com"),
                                                    new ContactDataDto(ContactTypes.FIXEDPHONE,"039544554"),
                                                    new ContactDataDto(ContactTypes.MOBILEPHONE,"048459498985"));
        LicensePlateDto licensePlate = new LicensePlateDto("A1-123-234", "KR");
        CreateMemberDto input = new CreateMemberDto("Bob","Bobson","some street","1","1000",
                "Bruxelles", contactData, MembershipLevels.GOLD,licensePlate);
        MemberDto expected = new MemberDto(1L,"Bob","Bobson","some street", "1",
                "1000", "Bruxelles",contactData,MembershipLevels.GOLD,licensePlate);

        // Given

        // When

        // Then

        MemberDto actual = memberManagement.createMember(input);
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}