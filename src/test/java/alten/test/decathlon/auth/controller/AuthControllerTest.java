package alten.test.decathlon.auth.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matcher.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import alten.test.decathlon.auth.dto.AccountRequest;
import alten.test.decathlon.auth.repository.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectmMapper;

    @BeforeEach
    void setup(){
        userRepository.deleteAll();
    }

    void testAccount_shouldCreateAccount() throws Exception {
        AccountRequest request = new AccountRequest();
        request.setEmail("email@email.fr");
        request.setFirstname("firstname");
        request.setPassword("password");
        request.setUsername("username");

        this.mockMvc.perform(post("/auth")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectmMapper.writeValueAsString(request)))
            .andExpect(status().isCreated());
    }

    @Test
    void testToken() {

    }
}
