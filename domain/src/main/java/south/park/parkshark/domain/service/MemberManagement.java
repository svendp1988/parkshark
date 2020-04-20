package south.park.parkshark.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import south.park.parkshark.domain.dto.request.CreateMemberDto;
import south.park.parkshark.domain.dto.response.MemberDto;
import south.park.parkshark.repositories.MemberRepository;

@Service
public class MemberManagement {
    private MemberRepository memberRepository;
    private MemberMapper memberMapper;

    @Autowired
    public MemberManagement(MemberRepository memberRepository, MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
    }

    public MemberDto createMember(CreateMemberDto input){
        return null;
    }

}
