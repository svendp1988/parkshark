package south.park.parkshark.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import south.park.parkshark.entities.Members;

@Repository
public interface MemberRepository extends CrudRepository<Members, Long> {

}
