package ducami.org.ducademi.domain.board.dto;

import ducami.org.ducademi.domain.member.entity.MemberEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BoardRequestDTO { // 강의 idx 필요, 파일 필요

    @NotBlank
    private String title;

    @NotBlank
    private String content;

}
