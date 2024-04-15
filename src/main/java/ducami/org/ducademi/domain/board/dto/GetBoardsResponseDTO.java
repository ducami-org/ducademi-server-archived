package ducami.org.ducademi.domain.board.dto;


import ducami.org.ducademi.domain.board.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class GetBoardsResponseDTO {

    private Long boardIdx;
    private String title;
    private LocalDateTime created;
    private LocalDateTime modified;

    public static GetBoardsResponseDTO of(BoardEntity boardEntity){
        return GetBoardsResponseDTO.builder()
                .boardIdx(boardEntity.getBoardIdx())
                .title(boardEntity.getTitle())
                .created(boardEntity.getCreated())
                .modified(boardEntity.getModified()).build();
    }
}
