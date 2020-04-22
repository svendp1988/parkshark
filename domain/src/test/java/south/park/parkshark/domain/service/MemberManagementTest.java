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

@ExtendWith(MockitoExtension.class)
class MemberManagementTest {
    private MemberManagement memberManagement;
    private MemberMapper memberMapper = new MemberMapper();

    private MemberRepository memberRepository = Mockito.mock(MemberRepository.class);
    private AddressRepository addressRepository = Mockito.mock(AddressRepository.class);
    private PersonRepository personRepository = Mockito.mock(PersonRepository.class);
    private LicensePlateRepository licensePlateRepository = Mockito.mock(LicensePlateRepository.class);
    private ContactDataRepository contactDataRepository = Mockito.mock(ContactDataRepository.class);

    public MemberManagementTest() {
        this.memberManagement = new MemberManagement(memberRepository, addressRepository, personRepository,
                licensePlateRepository, contactDataRepository , memberMapper, validationService);
    }

    @Test
    void registerAsMember_withoutAnEmail_thenThrow(){
        List<ContactDataDto> contactDataDto = List.of(
                new ContactDataDto(ContactTypes.FIXEDPHONE,"039544554"),
                new ContactDataDto(ContactTypes.MOBILEPHONE,"048459498985"));
        LicensePlateDto licensePlateDto = new LicensePlateDto("A1-123-234", "KR");
        CreateMemberDto input = new CreateMemberDto("Bob","Bobson","some street","1","1000",
                "Bruxelles", contactDataDto, MembershipLevels.GOLD, licensePlateDto);

        Assertions.assertThatExceptionOfType(LackingEmailAddressException.class)
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

        Assertions.assertThatExceptionOfType(InvalidEmailException.class)
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

        Assertions.assertThatExceptionOfType(LackingPhoneNumberException.class)
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
        Mockito.when(addressRepository.save(Mockito.any(Address.class))).thenReturn(address);

        List<ContactData> contactData = List.of(
                new ContactData(1L, 1L, ContactTypes.EMAIL, "bob@bobson.com"),
                new ContactData(2L, 1L, ContactTypes.FIXEDPHONE, "039544554"),
                new ContactData(3L, 1L, ContactTypes.MOBILEPHONE, "048459498985")
        );

        Person person = new Person(1L,address, "Bob", "Bobson", contactData);
        Mockito.when(personRepository.save(Mockito.any(Person.class))).thenReturn(person);

        LicensePlate licensePlate = new LicensePlate(1L,"A1-123-234", "KR");
        Mockito.when(licensePlateRepository.save(Mockito.any(LicensePlate.class))).thenReturn(licensePlate);

        Member member = new Member(1L, person,input.getMembershipLevel(), registrationDate, List.of(licensePlate));
        Mockito.when(memberRepository.save(Mockito.any(Member.class))).thenReturn(member);

        MemberDto actual = memberManagement.registerMember(input);
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}