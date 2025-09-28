package alten.test.decathlon.auth.utils;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {
    private final Key key;
    private final long expirationMs;

    public JwtUtils(@Value("${jwt.secret}") String base64Secret,
                    @Value("${jwt.expiration-ms}") long expirationMs) {
                        byte[]  keyBytes = Base64.getDecoder().decode(base64Secret);
                        this.key = Keys.hmacShaKeyFor(keyBytes);
                        this.expirationMs = expirationMs;
                    } 
    public String generateToken(String username) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + expirationMs);
        return Jwts.builder()
                    .setSubject(username)
                    .setIssuedAt(now)
                    .setExpiration(exp)
                    .signWith(key, SignatureAlgorithm.HS256)
                    .compact();
    }
    public String getUserNameFromToken(String token){
        return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try{
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e){
            return false;
        }
    }
}
