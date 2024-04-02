package ducami.org.ducademi.domain.member.service;

import ducami.org.ducademi.domain.member.authority.MemberAccountType;
import ducami.org.ducademi.domain.member.dto.MemberRegisterDTO;
import ducami.org.ducademi.domain.member.entity.MemberEntity;
import ducami.org.ducademi.domain.member.repository.MemberRepository;
import ducami.org.ducademi.global.exception.CustomErrorCode;
import ducami.org.ducademi.global.exception.CustomException;
import ducami.org.ducademi.global.response.BaseResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

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

}
