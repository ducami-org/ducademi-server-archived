package ducami.org.ducademi.global.auth;

import ducami.org.ducademi.domain.member.entity.MemberEntity;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtils {

    private final JwtProperties properties;
    private final SecretKey secretKey;

    public JwtUtils(JwtProperties properties) {
        this.properties = properties;
        this.secretKey = new SecretKeySpec(properties.getSecretKey().getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    public JwtInfo generateToken(MemberEntity member) {
        long now = new Date().getTime();

        String accessToken = Jwts.builder()
                .claim("sub", member.getIdx().toString())
                .claim("authority", member.getAuthority())
                .issuedAt(new Date(now))
                .expiration(new Date(now + properties.getAccessExpired()))
                .signWith(secretKey)
                .compact();

        String refreshToken = Jwts.builder()
                .claim("sub", member.getIdx().toString())
                .claim("authority", member.getAuthority())
                .issuedAt(new Date(now))
                .expiration(new Date(now + properties.getRefreshExpired()))
                .signWith(secretKey)
                .compact();

        return JwtInfo.of(accessToken, refreshToken);
    }

}
