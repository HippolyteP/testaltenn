package alten.test.decathlon.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountRequest {

    @NotBlank(message = "Username is required")
    private String username;
    @NotBlank(message = "Password is required")
    private String password;
    @NotBlank(message = "Firstname is required")
    private String firstname;
    @NotBlank(message = "Email is required")
    @Email(message="Email must be valid")
    private String email;

}
