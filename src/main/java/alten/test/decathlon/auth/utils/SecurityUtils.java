package alten.test.decathlon.auth.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import alten.test.decathlon.auth.entity.User;

@Component
public class SecurityUtils {
    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication()
        .getPrincipal();
        if (principal instanceof User user){
            return user;
        }
        throw new RuntimeException("No authentification user found");


    }

    public Long getCurrentUserId(){
        return getCurrentUser().getId();
    }

    public String getCurrentEmail(){
        return getCurrentUser().getEmail();
    }
}
