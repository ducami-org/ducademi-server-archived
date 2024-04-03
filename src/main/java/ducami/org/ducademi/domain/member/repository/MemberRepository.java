package ducami.org.ducademi.domain.member.repository;

import ducami.org.ducademi.domain.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    boolean existsById(String id);

}
