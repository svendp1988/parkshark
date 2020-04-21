package south.park.parkshark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import south.park.parkshark.domain.dto.request.CreateMemberDto;
import south.park.parkshark.domain.dto.response.MemberDto;
import south.park.parkshark.domain.service.MemberManagement;

@RestController
@RequestMapping(path = "/member", consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
public class MemberController {
    private final MemberManagement memberManagement;

    @Autowired
    MemberController(MemberManagement memberManagement){
        this.memberManagement = memberManagement;
    }

    @PostMapping
    public MemberDto registerAsMember(@RequestBody CreateMemberDto input){
        return memberManagement.registerMember(input);
    }


}
