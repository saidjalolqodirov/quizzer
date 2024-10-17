package uz.test.util;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import uz.test.config.UserDetailsImpl;

@UtilityClass
public class SecurityContextUtils {

    public static UserDetailsImpl getUser() {
        SecurityContext context = getContext();
        if (context == null) {
            throw new RuntimeException();
        }

        if (context.getAuthentication().getPrincipal() instanceof UserDetailsImpl) {
            return (UserDetailsImpl) context.getAuthentication().getPrincipal();
        }
        throw new RuntimeException();
    }

    SecurityContext getContext() {
        SecurityContext context = SecurityContextHolder.getContext();

        if (context == null || context.getAuthentication() == null) {
            return null;
        }
        return context;
    }
}
