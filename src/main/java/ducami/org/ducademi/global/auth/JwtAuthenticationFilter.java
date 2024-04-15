package ducami.org.ducademi.global.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import ducami.org.ducademi.global.exception.CustomErrorCode;
import ducami.org.ducademi.global.response.BaseResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");

        if (token != null) {
            if (!jwtUtils.isExpired(token)) {
                Authentication authentication = jwtUtils.getAuthentication(token);

                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                setErrorResponse(response, CustomErrorCode.JWT_WAS_EXPIRED);
                return;
            }
        }

        doFilter(request, response, filterChain);
    }

    public void setErrorResponse(
            HttpServletResponse response,
            CustomErrorCode errorCode
    ) throws IOException {
        response.setStatus(errorCode.getCode().value());
        response.setContentType("application/json;charset=UTF-8");

        response.getWriter().write(
                objectMapper.writeValueAsString(
                        BaseResponse.of(
                                false,
                                errorCode.getStatus(),
                                errorCode.getMessage(),
                                null
                        )
                )
        );
    }
}
