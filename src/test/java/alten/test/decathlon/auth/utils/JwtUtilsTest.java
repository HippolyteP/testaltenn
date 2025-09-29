package alten.test.decathlon.auth.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JwtUtilsTest {
    private JwtUtils jwtUtils;
    private String token;

    @BeforeEach
    void setUp(){
        jwtUtils = new JwtUtils("56t/l3K+77Xpw9YLGh9yUq88HbRKVBMDxahXPeWaVchfQOVeaqQ9Aj+aRPKGKiuT", 900000);
        token = jwtUtils.generateToken("email@email.fr");

    }
    @Test
    void testGenerateToken_NotNull() {
        assertNotNull(token, "Le token ne doit pas être null");

    }

    @Test
    void testGetUserNameFromToken() {
        String userName = jwtUtils.getUserNameFromToken(token);
        assertEquals("email@email.fr", userName);
    }

    @Test
    void testValidateToken_ValidToken() {
        boolean isValid = jwtUtils.validateToken(token);
        assertTrue(isValid, "Le token doit être valide pour 'email@email.fr");

    }

}
