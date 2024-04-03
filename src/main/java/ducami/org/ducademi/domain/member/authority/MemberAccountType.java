package ducami.org.ducademi.domain.member.authority;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum MemberAccountType {

    ROLE_USER,
    ROLE_DUCAMI,
    ROLE_ADMIN;

    public static GrantedAuthority convert(MemberAccountType type) {
        switch (type) {
            case ROLE_USER :
                return new SimpleGrantedAuthority("ROLE_USER");
            case ROLE_DUCAMI :
                return new SimpleGrantedAuthority("ROLE_DUCAMI");
            case ROLE_ADMIN :
                return new SimpleGrantedAuthority("ROLE_ADMIN");
            default :
                throw new IllegalArgumentException("잘못된 권한 이름");
        }
    }

}
