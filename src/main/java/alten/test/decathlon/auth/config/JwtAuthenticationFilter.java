package alten.test.decathlon.auth.config;

import java.io.IOException;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import alten.test.decathlon.auth.entity.User;
import alten.test.decathlon.auth.repository.UserRepository;
import alten.test.decathlon.auth.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;

    private final UserRepository userRepository;

    public JwtAuthenticationFilter(JwtUtils jwtUtils, UserRepository userRepository) {
        this.jwtUtils = jwtUtils;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        String token = null;
        String userEmail = null;

        if(header != null && header.startsWith("Bearer ")){
            token = header.substring(7);
        }

        if(token !=null && jwtUtils.validateToken(token)){
            userEmail = jwtUtils.getUserNameFromToken(token);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userEmail, null,null);
            SecurityContextHolder.getContext().setAuthentication(auth);
            if(request.getRequestURI().contains("/products") && !"admin@admin.com".equalsIgnoreCase(userEmail)) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.setContentType(("application/json"));
                response.getWriter().write(
                    "{\"status\":403,\"message\":\"Only the admin is allow to access to this ressource\",\"data\":null}"
                );
            }
             User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("User not found"));
             UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, null, null);
            SecurityContextHolder.getContext().setAuthentication(authToken);
            }   
        filterChain.doFilter(request, response);
    }

}
