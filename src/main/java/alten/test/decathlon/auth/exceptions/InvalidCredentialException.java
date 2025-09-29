package alten.test.decathlon.auth.exceptions;

public class InvalidCredentialException extends RuntimeException{
    public InvalidCredentialException(){
        super("InvalidCredential " );
    }
}
