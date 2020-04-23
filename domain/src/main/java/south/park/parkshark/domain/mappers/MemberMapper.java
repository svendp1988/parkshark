package south.park.parkshark.domain.mappers;

import org.springframework.stereotype.Service;
import south.park.parkshark.domain.dto.request.CreateMemberDto;
import south.park.parkshark.domain.dto.response.MemberDto;
import south.park.parkshark.domain.dto.shared.ContactDataDto;
import south.park.parkshark.domain.dto.shared.LicensePlateDto;
import south.park.parkshark.datastore.entities.ContactData;
import south.park.parkshark.datastore.entities.LicensePlate;
import south.park.parkshark.datastore.entities.Member;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberMapper {
    public MemberDto toMemberDto(Member member) {
        return new MemberDto(member.getMemberId(), member.getPerson().getFirstName(), member.getPerson().getLastName(),
                member.getPerson().getAddress().getStreetName(), member.getPerson().getAddress().getStreetNumber(),
                member.getPerson().getAddress().getPostalCode(), member.getPerson().getAddress().getPostalLabel(),
                toContactDataDto(member.getPerson().getContactDataList()),member.getMembershipLevel(),
                toLicensePlateDto(member.getLicensePlates()), member.getRegistrationDate());
    }

    public List<MemberDto> toMemberDto(List<Member> inputList){
        return inputList.stream().map(this::toMemberDto).collect(Collectors.toList());
    }

    public Member toMember(CreateMemberDto createMemberDto) {
        return new Member();
    }

//    public AddressDto toAddressDto() {
//
//    }
//
//    public ContactData toContactDataDto() {
//
//    }
    public List<ContactDataDto> toContactDataDto(List<ContactData> inputList){
        return inputList.stream().map(this::toContactDataDto).collect(Collectors.toUnmodifiableList());
    }
    public ContactDataDto toContactDataDto(ContactData input){
        return new ContactDataDto(input.getContactType(), input.getData());
    }
    public ContactData toContactData(ContactDataDto input, long personId){
        return new ContactData(personId,input.getType(),input.getData());
    }

    public List<LicensePlateDto> toLicensePlateDto(List<LicensePlate> inputList){
        return inputList.stream().map(this::toLicensePlateDto).collect(Collectors.toUnmodifiableList());
    }

    public LicensePlateDto toLicensePlateDto(LicensePlate input){
        return new LicensePlateDto(input.getLicenseNumber(),input.getCountry());
    }

    public LicensePlate toLicensePlate(LicensePlateDto input){
        return new LicensePlate(input.getPlateNumber(),input.getIssuingCountry());
    }

}
