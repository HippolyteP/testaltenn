package alten.test.decathlon.auth.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import alten.test.decathlon.auth.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;

    public JwtAuthenticationFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        String token = null;

        if(header != null && header.startsWith("Bearer ")){
            token = header.substring(7);
        }

        if(token !=null && jwtUtils.validateToken(token)){
            String username = jwtUtils.getUserNameFromToken(token);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null,null);
            SecurityContextHolder.getContext().setAuthentication(auth);
            if(request.getRequestURI().contains("/products") && !"admin@admin.com".equalsIgnoreCase(username)) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.setContentType(("application/json"));
                response.getWriter().write(
                    "{\"status\":403,\"message\":\"Only the admin is allow to access to this ressource\",\"data\":null}"
                );
            }
        }
        filterChain.doFilter(request, response);
    }

}
