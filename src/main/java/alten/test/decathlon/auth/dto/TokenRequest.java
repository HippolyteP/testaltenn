package alten.test.decathlon.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenRequest {

    @NotBlank(message = "Email is required")
    @Email(message="Email must be valid")
    private String email;
    @NotBlank(message = "Password is required")
    private String password;
}
