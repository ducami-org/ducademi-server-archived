package ducami.org.ducademi.domain.member.controller;

import ducami.org.ducademi.domain.member.dto.MemberRegisterDTO;
import ducami.org.ducademi.domain.member.service.MemberService;
import ducami.org.ducademi.global.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("register")
    public BaseResponse<?> register(@RequestBody MemberRegisterDTO dto) {
        return memberService.register(dto);
    }

}
