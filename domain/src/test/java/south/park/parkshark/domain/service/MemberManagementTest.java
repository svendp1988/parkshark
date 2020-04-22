package south.park.parkshark.domain.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import south.park.parkshark.domain.dto.shared.ContactDataDto;
import south.park.parkshark.domain.dto.request.CreateMemberDto;
import south.park.parkshark.domain.dto.response.MemberDto;
import south.park.parkshark.domain.dto.shared.LicensePlateDto;
import south.park.parkshark.entities.*;
import south.park.parkshark.mappers.MemberMapper;
import south.park.parkshark.repositories.*;
import south.park.parkshark.domain.exceptions.*;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberManagementTest {
    private MemberManagement memberManagement;
    private MemberMapper memberMapper = new MemberMapper();

    private MemberRepository memberRepository = mock(MemberRepository.class);
    private AddressRepository addressRepository = mock(AddressRepository.class);
    private PersonRepository personRepository = mock(PersonRepository.class);
    private LicensePlateRepository licensePlateRepository = mock(LicensePlateRepository.class);
    private ContactDataRepository contactDataRepository = mock(ContactDataRepository.class);

    public MemberManagementTest() {
        this.memberManagement = new MemberManagement(memberRepository, addressRepository, personRepository,
                licensePlateRepository, contactDataRepository , memberMapper);
    }

    @Test
    void registerAsMember_withoutAnEmail_thenThrow(){
        List<ContactDataDto> contactDataDto = List.of(
                new ContactDataDto(ContactTypes.FIXEDPHONE,"039544554"),
                new ContactDataDto(ContactTypes.MOBILEPHONE,"048459498985"));
        LicensePlateDto licensePlateDto = new LicensePlateDto("A1-123-234", "KR");
        CreateMemberDto input = new CreateMemberDto("Bob","Bobson","some street","1","1000",
                "Bruxelles", contactDataDto, MembershipLevels.GOLD, licensePlateDto);

        assertThatExceptionOfType(LackingEmailAddressException.class)
                .isThrownBy(() -> memberManagement.registerMember(input));
    }

    @Test
    void registerAsMember_withoutAValidEmail_thenThrow(){

        List<ContactDataDto> contactDataDto = List.of(
                new ContactDataDto(ContactTypes.EMAIL,"bob@bobson"),
                new ContactDataDto(ContactTypes.FIXEDPHONE,"039544554"),
                new ContactDataDto(ContactTypes.MOBILEPHONE,"048459498985")
        );
        LicensePlateDto licensePlateDto = new LicensePlateDto("A1-123-234", "KR");
        CreateMemberDto input = new CreateMemberDto("Bob","Bobson","some street","1","1000",
                "Bruxelles", contactDataDto, MembershipLevels.GOLD, licensePlateDto);

        assertThatExceptionOfType(InvalidEmailException.class)
                .isThrownBy(() -> memberManagement.registerMember(input));
    }
    @Test
    void registerAsMember_withoutValidPhoneNumber_thenThrow(){

        List<ContactDataDto> contactDataDto = List.of(
                new ContactDataDto(ContactTypes.EMAIL,"bob@bobson.com")
                );
        LicensePlateDto licensePlateDto = new LicensePlateDto("A1-123-234", "KR");
        LocalDate registrationDate = LocalDate.now();
        CreateMemberDto input = new CreateMemberDto("Bob","Bobson","some street","1","1000",
                "Bruxelles", contactDataDto, MembershipLevels.GOLD, licensePlateDto);

        assertThatExceptionOfType(LackingPhoneNumberException.class)
                .isThrownBy(() -> memberManagement.registerMember(input));
    }

    @Test
    void registerAsMember_returnsADto() {
        List<ContactDataDto> contactDataDto = List.of(new ContactDataDto(ContactTypes.EMAIL,"bob@bobson.com"),
                                                    new ContactDataDto(ContactTypes.FIXEDPHONE,"039544554"),
                                                    new ContactDataDto(ContactTypes.MOBILEPHONE,"048459498985"));
        LicensePlateDto licensePlateDto = new LicensePlateDto("A1-123-234", "KR");
        LocalDate registrationDate = LocalDate.now();
        CreateMemberDto input = new CreateMemberDto("Bob","Bobson","some street","1","1000",
                "Bruxelles", contactDataDto, MembershipLevels.GOLD, licensePlateDto);
        MemberDto expected = new MemberDto(1L,"Bob","Bobson","some street", "1",
                "1000", "Bruxelles",contactDataDto,MembershipLevels.GOLD,List.of(licensePlateDto), registrationDate);

        Address address = new Address(1L,"some street", "1", "1000", "Bruxelles");
        when(addressRepository.save(any(Address.class))).thenReturn(address);

        List<ContactData> contactData = List.of(
                new ContactData(1L, 1L, ContactTypes.EMAIL, "bob@bobson.com"),
                new ContactData(2L, 1L, ContactTypes.FIXEDPHONE, "039544554"),
                new ContactData(3L, 1L, ContactTypes.MOBILEPHONE, "048459498985")
        );

        Person person = new Person(1L,address, "Bob", "Bobson", contactData);
        when(personRepository.save(any(Person.class))).thenReturn(person);

        LicensePlate licensePlate = new LicensePlate(1L,"A1-123-234", "KR");
        when(licensePlateRepository.save(any(LicensePlate.class))).thenReturn(licensePlate);

        Member member = new Member(1L, person,input.getMembershipLevel(), registrationDate, List.of(licensePlate));
        when(memberRepository.save(any(Member.class))).thenReturn(member);

        MemberDto actual = memberManagement.registerMember(input);
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    void getAllMembers_returnsList(){

        List<ContactData> contactData = List.of(
                new ContactData(1L, 1L, ContactTypes.EMAIL, "bob@bobson.com"),
                new ContactData(2L, 1L, ContactTypes.FIXEDPHONE, "039544554"),
                new ContactData(3L, 1L, ContactTypes.MOBILEPHONE, "048459498985")
        );
        Address address = new Address(1L,"some street", "1", "1000", "Bruxelles");
        Person person = new Person(1L,address, "Bob", "Bobson", contactData);
        LicensePlate licensePlate = new LicensePlate(1L,"A1-123-234", "KR");
        Member member = new Member(1L, person, MembershipLevels.GOLD, LocalDate.now(), List.of(licensePlate));
        List<Member> allMembers = List.of(member);
        when(memberRepository.findAll()).thenReturn(allMembers);
        assertThat(memberManagement.findAll()).contains(memberMapper.toMemberDto(member));
    }

}