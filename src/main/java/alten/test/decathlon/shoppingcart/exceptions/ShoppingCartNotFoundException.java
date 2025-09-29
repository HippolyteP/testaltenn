package alten.test.decathlon.shoppingcart.exceptions;

public class ShoppingCartNotFoundException extends RuntimeException{
    public ShoppingCartNotFoundException(Long id){
        super("Shopping cart with id " + id + "not found");
    }
}
