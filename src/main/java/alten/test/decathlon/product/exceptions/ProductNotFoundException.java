package alten.test.decathlon.product.exceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(Long id){
        super("Product with id " + id + "not found");
    }

        public ProductNotFoundException(String code){
        super("Product with code " + code + "not found");
    }
}
