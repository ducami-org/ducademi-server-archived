package ducami.org.ducademi.domain.board.repository;

import ducami.org.ducademi.domain.board.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

//import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {// 여기다가 회원인지 아닌지 확인하는 게 있어야 하나

}
