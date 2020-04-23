package south.park.parkshark.datastore.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import south.park.parkshark.datastore.entities.Member;

import java.util.List;

@Repository
public interface MemberRepository extends CrudRepository<Member, Long> {
    @Override
    List<Member> findAll();
}
