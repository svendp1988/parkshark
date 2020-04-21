package south.park.parkshark.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import south.park.parkshark.domain.dto.request.CreateMemberDto;
import south.park.parkshark.domain.dto.response.MemberDto;
import south.park.parkshark.domain.dto.shared.ContactDataDto;
import south.park.parkshark.entities.*;
import south.park.parkshark.repositories.*;
import south.park.parkshark.domain.exceptions.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MemberManagement {
    private final MemberRepository memberRepository;
    private final AddressRepository addressRepository;
    private final PersonRepository personRepository;
    private final LicensePlateRepository licensePlateRepository;
    private final ContactDataRepository contactDataRepository;
    private final MemberMapper memberMapper;

    @Autowired
    public MemberManagement(MemberRepository memberRepository, AddressRepository addressRepository,
                            PersonRepository personRepository , LicensePlateRepository licensePlateRepository,
                            ContactDataRepository contactDataRepository, MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.addressRepository = addressRepository;
        this.personRepository = personRepository;
        this.licensePlateRepository = licensePlateRepository;
        this.memberMapper = memberMapper;
        this.contactDataRepository = contactDataRepository;
    }

    public MemberDto registerMember(CreateMemberDto input){
        assertAtLeastOneEmailExistsAndAllAreValid(input);
        assertAtLeastOnePhoneNumberIsGiven(input);

        Member member = createMemberFromDto(input);
        return memberMapper.toMemberDto(member);
    }

    private void assertAtLeastOnePhoneNumberIsGiven(CreateMemberDto input) {
        if(input.getContactData().stream()
                .filter(cd -> cd.getType() == ContactTypes.FIXEDPHONE || cd.getType() == ContactTypes.MOBILEPHONE)
                .collect(Collectors.toUnmodifiableList()).size() == 0){
            throw new LackingPhoneNumberException();
        }
    }

    private void assertAtLeastOneEmailExistsAndAllAreValid(CreateMemberDto input) {
        List<ContactDataDto> emails = input.getContactData().stream().filter(cd -> cd.getType() == ContactTypes.EMAIL).collect(Collectors.toUnmodifiableList());

        if(emails.size() == 0) throw new LackingEmailAddressException();
        assertEmailsHaveValidForm(emails);
    }

    private void assertEmailsHaveValidForm(List<ContactDataDto> emails) {
        String emailMatch = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        emails.forEach(cd -> { if(!cd.getData().matches(emailMatch)) throw new InvalidEmailException();});
    }

    private Member createMemberFromDto(CreateMemberDto input) {
        final Address address = extractAndCreateAddress(input);
        final Person person = extractAndCreatePersonWithContactData(input, address);
        final LicensePlate licensePlate = extractAndCreateLicensePlate(input);

        Member member = new Member(person,input.getMembershipLevel(), LocalDate.now(), List.of(licensePlate));
        member = memberRepository.save(member);
        return member;
    }

    private LicensePlate extractAndCreateLicensePlate(CreateMemberDto input) {
        return licensePlateRepository.save(memberMapper.toLicensePlate(input.getLicensePlate()));
    }

    private Person extractAndCreatePersonWithContactData(CreateMemberDto input, Address address) {
        final Person person = personRepository.save(new Person(address,input.getFirstName(),input.getLastName()));
        List<ContactData> contactData = input.getContactData().stream()
                .map(cd -> memberMapper.toContactData(cd, person.getPersonId()))
                .collect(Collectors.toList());
        contactDataRepository.saveAll(contactData);
        person.setContactData(contactData);
        return person;
    }

    private Address extractAndCreateAddress(CreateMemberDto input) {
        Address address = new Address(input.getStreetName(), input.getStreetNumber(), input.getPostalCode(), input.getPostalLabel());
        address = addressRepository.save(address);
        return address;
    }

}
