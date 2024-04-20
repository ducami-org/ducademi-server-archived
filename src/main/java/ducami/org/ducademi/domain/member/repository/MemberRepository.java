package ducami.org.ducademi.domain.member.repository;

import ducami.org.ducademi.domain.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    boolean existsById(String id);

    Optional<MemberEntity> findById(String id);

    Optional<MemberEntity> findByEmail(String email);

    MemberEntity findByIdx(Long idx);


}
