package ducami.org.ducademi.domain.member.controller;

import ducami.org.ducademi.domain.member.dto.MemberLoginDTO;
import ducami.org.ducademi.domain.member.dto.MemberRegisterDTO;
import ducami.org.ducademi.domain.member.service.MemberService;
import ducami.org.ducademi.global.response.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("register")
    public BaseResponse<?> postRegister(@Valid @RequestBody MemberRegisterDTO dto) {
        return memberService.postRegister(dto);
    }

    @PostMapping("login")
    public BaseResponse<?> postLogin(@Valid @RequestBody MemberLoginDTO dto) {
        return memberService.postLogin(dto);
    }

    @GetMapping("oauth2")
    public void getOAuth2(String code, String registrationId) {

    }

}
