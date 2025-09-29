package alten.test.decathlon.shoppingcart.exceptions;

public class UserNotFoundInShoppingCartException extends RuntimeException{
    public UserNotFoundInShoppingCartException(Long id){
        super("user " + id + "does not have shopping cart");
    }
}
