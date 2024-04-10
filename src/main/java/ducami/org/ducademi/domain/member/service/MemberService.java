package ducami.org.ducademi.domain.member.service;

import com.fasterxml.jackson.databind.JsonNode;
import ducami.org.ducademi.domain.member.authority.MemberAccountType;
import ducami.org.ducademi.domain.member.dto.MemberLoginDTO;
import ducami.org.ducademi.domain.member.dto.MemberRegisterDTO;
import ducami.org.ducademi.domain.member.entity.MemberEntity;
import ducami.org.ducademi.domain.member.repository.MemberRepository;
import ducami.org.ducademi.global.auth.JwtUtils;
import ducami.org.ducademi.global.exception.CustomErrorCode;
import ducami.org.ducademi.global.exception.CustomException;
import ducami.org.ducademi.global.property.OAuth2Properties;
import ducami.org.ducademi.global.response.BaseResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtUtils jwtUtils;
    private final RestTemplate restTemplate;
    private final OAuth2Properties oAuth2Properties;

    public BaseResponse<?> postRegister(MemberRegisterDTO dto) {
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
                        .email(dto.getEmail())
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

    public BaseResponse<?> postLogin(MemberLoginDTO dto) {
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

    public void getOAuth2(String code, String registrationId) {
        String token = this.getAccessToken(code);
    }

    public String getAccessToken(String code) {
        Map<String, String> params = new HashMap<>();

        params.put("code", code);
        params.put("client_id", oAuth2Properties.getGoogleId());
        params.put("client_secret", oAuth2Properties.getGoogleSecret());
        params.put("redirect-uri", oAuth2Properties.getGoogleRedirectURI());
        params.put("grant_type", "authorization_code");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<?> httpEntity = new HttpEntity<>(params, httpHeaders);

        ResponseEntity<JsonNode> responseEntity = restTemplate.exchange(
                oAuth2Properties.getGoogleTokenURI(),
                HttpMethod.POST,
                httpEntity,
                JsonNode.class
        );

        return responseEntity.getBody().get("access_token").asText();
    }

    public JsonNode getUserResource(String token) {
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.set("Authorization", "Bearer " + token);

        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);

        return restTemplate.exchange(
                oAuth2Properties.getGoogleResourceURI(),
                HttpMethod.GET,
                httpEntity,
                JsonNode.class
        ).getBody();
    }

}
