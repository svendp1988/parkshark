package south.park.parkshark.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import south.park.parkshark.entities.Member;
import south.park.parkshark.entities.MembershipLevel;
import south.park.parkshark.entities.MembershipLevels;
import south.park.parkshark.entities.Person;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
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

    @Test
    @Sql({"defaultAddress.sql", "createMemberDependencies.sql"})
    public void createMemberWithMembershipLevel() {
        Person person = new Person(1l, null, null, null, null);
        Member member = new Member(1l, person, MembershipLevels.GOLD, LocalDate.now(), List.of());
        memberRepository.save(member);
        Member actualMember = memberRepository.findById(1l).get();
        assertThat(actualMember.getMembershipLevel()).isEqualTo(MembershipLevels.GOLD);
    }

    @Test
    @Sql({"defaultAddress.sql", "createMemberDependencies.sql"})
    public void createMemberWithoutMembershipLevel() {
        Person person = new Person(1l, null, null, null, null);
        Member member = new Member(person, LocalDate.now(), List.of());
        memberRepository.save(member);
        Member actualMember = memberRepository.findById(1l).get();
        assertThat(actualMember.getMembershipLevel()).isEqualTo(MembershipLevels.BRONZE);
    }
    @Test
    @Sql({"defaultAddress.sql","getAllMembers.sql"})
    public void getAllMembersFromH2() {
        Iterable<Member> actualList = memberRepository.findAll();
        Member actualMemberById = memberRepository.findById(2L).get();
        assertThat(actualList).hasSize(4);
        assertThat(actualList).contains(actualMemberById);

    }

    @Test
    @Sql({"defaultAddress.sql", "getAllMembers.sql"})
    public void getAllMembers() {
        Iterable<Member> actualList = memberRepository.findAll();
        Member actualMemberById = memberRepository.findById(2L).get();
        assertThat(actualList).hasSize(4);
        assertThat(actualList).contains(actualMemberById);
    }
}
