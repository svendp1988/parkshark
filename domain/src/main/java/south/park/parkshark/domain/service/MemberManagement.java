package south.park.parkshark.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import south.park.parkshark.domain.dto.request.CreateMemberDto;
import south.park.parkshark.domain.dto.response.MemberDto;
import south.park.parkshark.entities.*;
import south.park.parkshark.repositories.AddressRepository;
import south.park.parkshark.repositories.LicensePlateRepository;
import south.park.parkshark.repositories.MemberRepository;
import south.park.parkshark.repositories.PersonRepository;

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
    private final MemberMapper memberMapper;

    @Autowired
    public MemberManagement(MemberRepository memberRepository, AddressRepository addressRepository,
                            PersonRepository personRepository , LicensePlateRepository licensePlateRepository,
                            MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.addressRepository = addressRepository;
        this.personRepository = personRepository;
        this.licensePlateRepository = licensePlateRepository;
        this.memberMapper = memberMapper;
    }

    public MemberDto registerMember(CreateMemberDto input){
        Member member = createMemberFromDto(input);
        return memberMapper.toMemberDto(member);
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
        person.setContactData(contactData);
        return person;
    }

    private Address extractAndCreateAddress(CreateMemberDto input) {
        Address address = new Address(input.getStreetName(), input.getStreetNumber(), input.getPostalCode(), input.getPostalLabel());
        address = addressRepository.save(address);
        return address;
    }

}
