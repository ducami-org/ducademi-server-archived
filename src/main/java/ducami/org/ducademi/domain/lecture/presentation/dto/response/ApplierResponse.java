package ducami.org.ducademi.domain.lecture.presentation.dto.response;


import ducami.org.ducademi.domain.member.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.Period;

@Getter
@Builder
@AllArgsConstructor
public class ApplierResponse {
    private String name;
    private String phoneNum;
    private String email;
    private int age;

    public static ApplierResponse of(MemberEntity memberEntity){
        return ApplierResponse.builder()
                .name(memberEntity.getName())
                .phoneNum(memberEntity.getPhoneNum())
                .email(memberEntity.getEmail())
                .age(Period.between(memberEntity.getBirth(), LocalDate.now()).getYears()).build();
    }

}
