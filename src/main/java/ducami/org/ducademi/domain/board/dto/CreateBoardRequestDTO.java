package ducami.org.ducademi.domain.board.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CreateBoardRequestDTO { // 강의 idx 필요, 파일 필요

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private Long lectureIdx;

}