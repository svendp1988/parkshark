package south.park.parkshark.domain.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import south.park.parkshark.repositories.MemberRepository;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberManagmentTest {
    private MemberManagment memberManagment;
    private MemberMapper memberMapper = new MemberMapper();

    @MockBean
    private MemberRepository memberRepository;

    public MemberManagmentTest() {
        this.memberManagment = new MemberManagment(memberRepository, memberMapper);
    }

    @Test
    void registerAsMember_returnsADto() {
        // Given
        when(memberRepository);
        // When

        // Then

    }
}