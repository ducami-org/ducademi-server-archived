package ducami.org.ducademi.domain.member.entity;

import ducami.org.ducademi.domain.member.authority.MemberAccountType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "member")
@NoArgsConstructor
public class MemberEntity {

    // 회원 PK
    @Id
    @Column(name = "idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    // 회원 아이디
    @Column(name = "id", nullable = false)
    private String id;

    // 회원 비밀번호
    @Column(name = "password", nullable = false)
    private String password;

    // 회원 실명
    @Column(name = "name", nullable = false)
    private String name;

    // 회원 생년월일
    @Column(name = "birth", nullable = false)
    private String birth;

    @Column(name="email", nullable = false, unique = true)
    private String email;

    // 회원 전화번호
    @Column(name = "phone_num", nullable = false)
    private String phoneNum;

    // 회원 권한
    @Column(name = "authority", nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberAccountType authority;

    // 회원 프로필 사진 링크
    @Column(name = "profile", nullable = false)
    private String profile;

    // 회원 소개글
    @Column(name = "introduction", nullable = false)
    private String introduction;

    // 회원 가입일
    @Column(name = "created_date", nullable = false)
    private LocalDate created_date;

    @Builder
    public MemberEntity (
            String id,
            String password,
            String name,
            String birth,
            String email,
            String phoneNum,
            MemberAccountType authority
    ) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.birth = birth;
        this.email = email;
        this.phoneNum = phoneNum;
        this.authority = authority;
        this.profile = "https://media.discordapp.net/attachments/1034098844092342294/1073150777033494548/Screenshot_20230129_022516_Photo_Editor.jpg?ex=6617980b&is=6605230b&hm=2aa70e554a6bda71f6c0514b3492ed10f180cdadfbcdba00f091ddd6c2c200da&=&";
        this.introduction = "";
        this.created_date = LocalDate.now();
    }

}
