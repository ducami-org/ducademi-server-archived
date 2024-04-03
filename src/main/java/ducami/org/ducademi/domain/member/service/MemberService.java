package ducami.org.ducademi.domain.member.service;

import ducami.org.ducademi.domain.member.authority.MemberAccountType;
import ducami.org.ducademi.domain.member.dto.MemberLoginDTO;
import ducami.org.ducademi.domain.member.dto.MemberRegisterDTO;
import ducami.org.ducademi.domain.member.entity.MemberEntity;
import ducami.org.ducademi.domain.member.repository.MemberRepository;
import ducami.org.ducademi.global.auth.JwtInfo;
import ducami.org.ducademi.global.auth.JwtUtils;
import ducami.org.ducademi.global.exception.CustomErrorCode;
import ducami.org.ducademi.global.exception.CustomException;
import ducami.org.ducademi.global.response.BaseResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtUtils jwtUtils;

    public BaseResponse<?> register(MemberRegisterDTO dto) {
        if (memberRepository.existsById(dto.getId())) {
            throw new CustomException(CustomErrorCode.MEMBER_ALREADY_EXIST);
        }

        memberRepository.save(
                MemberEntity.builder()
                        .id(dto.getId())
                        .password(
                                bCryptPasswordEncoder.encode(dto.getPassword())
                        )
                        .name(dto.getName())
                        .birth(dto.getBirth())
                        .phoneNum(dto.getPhoneNum())
                        .authority(MemberAccountType.ROLE_USER)
                        .build()
        );

        return BaseResponse.of(
                true,
                "OK",
                "회원가입 성공",
                null
        );
    }

    public BaseResponse<?> login(MemberLoginDTO dto) {
        MemberEntity member = memberRepository.findById(dto.getId())
                .orElseThrow(() -> new CustomException(CustomErrorCode.MEMBER_NOT_EXIST));

        if (!bCryptPasswordEncoder.matches(dto.getPassword(), member.getPassword())) {
            throw new CustomException(CustomErrorCode.MEMBER_NOT_CORRECT);
        }

        return BaseResponse.of(
                true,
                "OK",
                "로그인 성공",
                List.of(
                        jwtUtils.generateToken(member)
                )
        );
    }

}
