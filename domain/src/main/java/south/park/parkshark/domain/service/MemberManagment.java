package south.park.parkshark.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import south.park.parkshark.repositories.MemberRepository;

@Service
public class MemberManagment {
    private MemberRepository memberRepository;
    private MemberMapper memberMapper;

    @Autowired
    public MemberManagment(MemberRepository memberRepository, MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
    }

}
