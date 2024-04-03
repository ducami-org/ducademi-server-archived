package ducami.org.ducademi.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class MemberLoginDTO {

    @NotBlank
    private String id;

    @NotBlank
    private String password;

}
