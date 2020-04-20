package south.park.parkshark.domain.service;

import org.springframework.stereotype.Service;
import south.park.parkshark.domain.dto.request.CreateMemberDto;
import south.park.parkshark.domain.dto.response.MemberDto;
import south.park.parkshark.entities.Member;

@Service
public class MemberMapper {
    public MemberDto toMemberDto() {
        return null;
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
}
