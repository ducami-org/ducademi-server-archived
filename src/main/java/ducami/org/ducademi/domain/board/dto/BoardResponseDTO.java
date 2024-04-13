package ducami.org.ducademi.domain.board.dto;

import ducami.org.ducademi.domain.board.entity.BoardEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
public class BoardResponseDTO { // 강의 idx 필요, 맴버 idx 필요

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    public BoardResponseDTO(BoardEntity board) {
        this.title = board.getTitle();
        this.content = board.getContent();
    }
}
