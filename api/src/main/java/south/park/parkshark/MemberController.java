package south.park.parkshark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import south.park.parkshark.domain.dto.request.CreateMemberDto;
import south.park.parkshark.domain.dto.response.MemberDto;
import south.park.parkshark.domain.service.MemberManagement;

import java.util.List;

@RestController
@RequestMapping(path = "/member", consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
public class MemberController {
    private final MemberManagement memberManagement;

    @Autowired
    MemberController(MemberManagement memberManagement){
        this.memberManagement = memberManagement;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MemberDto registerAsMember(@RequestBody CreateMemberDto input){
        return memberManagement.registerMember(input);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MemberDto> findAll() {
        return memberManagement.findAll();
    }
}
