package ducami.org.ducademi.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class MemberRegisterDTO {

    @NotBlank
    private String id;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @NotBlank
    private String birth;

    @NotBlank
    private String email;

    @NotBlank
    private String phoneNum;

}
