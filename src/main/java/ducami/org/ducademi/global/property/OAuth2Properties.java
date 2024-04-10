package ducami.org.ducademi.global.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class OAuth2Properties {

    @Value("${oauth.google.client-id}") String googleId;
    @Value("${oauth.google.client-secret}") String googleSecret;
    @Value("${oauth.google.redirect-uri}") String googleRedirectURI;
    @Value("${oauth.google.token-uri}") String googleTokenURI;
    @Value("${oauth.google.resource-uri}") String googleResourceURI;

}
