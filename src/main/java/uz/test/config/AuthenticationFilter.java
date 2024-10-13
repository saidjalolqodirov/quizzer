package uz.test.config;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.test.users.UserEntity;
import uz.test.users.UserService;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.AccessDeniedException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {

    private final UserService userService;

    private final TokenUtil tokenUtil;


    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {
        if (!request.getMethod().equalsIgnoreCase("OPTIONS")) {
            String headerName = "Authorization";
            final String authHeader = request.getHeader(headerName);
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                if (tokenUtil.validateToken(token)) {
                    UUID userId = UUID.fromString(tokenUtil.getUsernameFromToken(token));
                    UserEntity userEntity;
                    try {
                        userEntity = userService.findById(userId);
                    } catch (Exception e) {
                        throw new AccessDeniedException("User not found");
                    }
                    UserDetailsImpl userDetails = new UserDetailsImpl(userEntity);
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    throw new AccessDeniedException("Token expired!");
                }
            }
            chain.doFilter(request, response);
        }
    }
}