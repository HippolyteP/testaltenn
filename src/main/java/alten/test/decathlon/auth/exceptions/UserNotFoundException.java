package alten.test.decathlon.auth.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String email){
        super("User : " + email + "not found");
    }
}
