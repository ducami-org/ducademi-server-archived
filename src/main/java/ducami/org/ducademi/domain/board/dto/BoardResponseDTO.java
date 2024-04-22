package ducami.org.ducademi.domain.board.dto;

import ducami.org.ducademi.domain.board.entity.BoardEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@Builder
@AllArgsConstructor
public class BoardResponseDTO { // 강의 idx 필요, 맴버 idx 필요

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private Long lectureIdx;

    @NotBlank
    private Long memberIdx;

    @NotBlank
    private LocalDateTime created;

    @NotBlank
    private LocalDateTime modified;


    public static BoardResponseDTO of(BoardEntity boardEntity){
        return BoardResponseDTO.builder()
                .title(boardEntity.getTitle())
                .content(boardEntity.getContent())
                .lectureIdx(boardEntity.getLectureIdx())
                .memberIdx(boardEntity.getMemberIdx())
                .created(boardEntity.getCreated())
                .modified(boardEntity.getModified()).build();
    }
}
