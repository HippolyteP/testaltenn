package alten.test.decathlon.shoppingcart.exceptions;

public class ShoppingCartConflictException extends RuntimeException{
    public ShoppingCartConflictException(Long id){
        super("Shopping cart already exist for the user " + id );
    }
}
