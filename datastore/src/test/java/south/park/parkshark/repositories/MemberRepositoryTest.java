package south.park.parkshark.repositories;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import south.park.parkshark.ParksharkApplication;


@AutoConfigureTestDatabase
@DataJpaTest
@ContextConfiguration(classes = {ParksharkApplication.class})
public class MemberRepositoryTest {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberRepositoryTest(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Test
    public void weExist(){
        Assertions.assertThat(memberRepository).isNotNull();
    }
}
