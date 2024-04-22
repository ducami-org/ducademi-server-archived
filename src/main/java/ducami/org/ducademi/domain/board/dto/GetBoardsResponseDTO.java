package ducami.org.ducademi.domain.board.dto;


import ducami.org.ducademi.domain.board.entity.BoardEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class GetBoardsResponseDTO {

    @NotBlank
    private Long boardIdx;

    @NotBlank
    private String title;

    @NotBlank
    private LocalDateTime created;

    @NotBlank
    private LocalDateTime modified;

    public static GetBoardsResponseDTO of(BoardEntity boardEntity){
        return GetBoardsResponseDTO.builder()
                .boardIdx(boardEntity.getBoardIdx())
                .title(boardEntity.getTitle())
                .created(boardEntity.getCreated())
                .modified(boardEntity.getModified()).build();
    }
}
