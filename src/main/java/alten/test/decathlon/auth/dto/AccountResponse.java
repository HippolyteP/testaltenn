package alten.test.decathlon.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountResponse {

    private String password;
    private String email;

    public AccountResponse( String email, String password){
        this.email = email;
        this.password = password;
    }

}
