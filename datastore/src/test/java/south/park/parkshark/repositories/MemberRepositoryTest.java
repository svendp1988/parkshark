package south.park.parkshark.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.jdbc.Sql;
import south.park.parkshark.entities.Member;
import south.park.parkshark.entities.MembershipLevels;
import south.park.parkshark.entities.Person;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@AutoConfigureTestDatabase
@DataJpaTest
@ComponentScan(basePackages = "south.park.parkshark")
public class MemberRepositoryTest {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberRepositoryTest(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Test
    public void weExist(){
        assertThat(memberRepository).isNotNull();
    }

    @Test
    @Sql({"defaultAddress.sql", "createMemberDependencies.sql"})
    public void createMember() {
        Person person = new Person(1l, null, null, null, null);
        Member member = new Member(1l, person, MembershipLevels.BRONZE, LocalDate.now(), List.of());
        memberRepository.save(member);

        assertThat(memberRepository.findById(1l)).isNotEmpty().hasValue(member);
    }
}
