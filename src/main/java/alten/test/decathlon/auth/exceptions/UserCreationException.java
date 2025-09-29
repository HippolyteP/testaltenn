package alten.test.decathlon.auth.exceptions;

public class UserCreationException extends RuntimeException{
    public UserCreationException(String email){
        super("User already in use " + email );
    }
}
