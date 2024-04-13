package ducami.org.ducademi.domain.board.entity;

import ducami.org.ducademi.domain.board.dto.BoardRequestDTO;
import ducami.org.ducademi.global.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "board")
public class BoardEntity extends BaseTimeEntity {

    // 게시글 PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardIdx;

    // 게시글 이름
    @Column(nullable = false)
    private String title;

    // 게시글 내용
    @Column(nullable = false)
    private String content;

    // 멤버 idx
    @Column(nullable = false)
    private Long memberIdx;

    // 파일 모음, 일단 보류
//    @OneToMany(mappedBy = "board")
//    private List<FileEntity> files = new ArrayList<>();

    @Builder
    public BoardEntity ( // 객체 생성 편리 위함
        String title,
        String content
    ) {
        this.title = title;
        this.content = content;
    }

    public void update(BoardRequestDTO boardRequestDTO) {
        this.title = boardRequestDTO.getTitle();
        this.content = boardRequestDTO.getContent();
    }
}
